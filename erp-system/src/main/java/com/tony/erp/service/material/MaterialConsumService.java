package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.MaterialConsumeMapper;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.*;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.DetailService;
import com.tony.erp.service.PurchaseOrderService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static com.tony.erp.constant.Constant.STRING_FOUR;
import static com.tony.erp.constant.Constant.STRING_ONE;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class MaterialConsumService {

    @Autowired
    private MaterialConsumeMapper materialConsumeMapper;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;
    /**
     * 分页查询所有领料记录
     *
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAll(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<MaterialConsume> consumes = materialConsumeMapper.selectAll();
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(consumes);
        PageInfo<MaterialConsume> pageInfo = new PageInfo<>(consumes);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    /**
     * 添加领料记录
     *
     * @param consume
     * @return
     */
    public int addMConsume(MaterialConsume consume) {
        consume.setMcId(KeyGeneratorUtils.keyUUID());
        log.info("领料单创建人[{}],时间[{}]", CurrentUser.getCurrentUser().getUname(), KeyGeneratorUtils.dateGenerator());
        consume.setMcStatus(Constant.STRING_ONE);
        List<BomDetail> details=detailService.selectByBCode(consume.getMcMSn());
        if(CollectionUtils.isEmpty(details)){
            return Constant.ARG_NOT_MATCHED;
        }
        for (BomDetail detail : details) {
            Material material = materialService.checkSnExist(detail.getMaterial().getmSn());
            int i = material.getmCount() - Math.round(detail.getBdNum() * (1 + detail.getBdRate()) * consume.getMcCountNeeded());
            if (i < 0) {
                return Constant.NUMBER_TOO_BIG;
            }
        }
        return materialConsumeMapper.insertSelective(consume);
    }

    /**
     * 更新领料记录
     *
     * @param consume
     * @return
     */
    public int upMConsum(MaterialConsume consume) {
        MaterialConsume mc = materialConsumeMapper.selectByPrimaryKey(consume.getMcId());
        if (STRING_FOUR.equals(mc.getMcStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        if (Constant.STRING_TWO.equals(mc.getMcStatus()) || Constant.STRING_THREE.equals(mc.getMcStatus())) {
            consume.setMcStatus(Constant.STRING_ONE);
            consume.setMcRequestor("");
        }
        return materialConsumeMapper.updateByPrimaryKeySelective(consume);
    }


    /**
     * 审核领料记录
     *
     * @param consume
     * @return
     */
    public int verifyConsume(MaterialConsume consume) {
        consume.setMcRequestor(CurrentUser.getCurrentUser().getUname());
        return materialConsumeMapper.updateByPrimaryKeySelective(consume);
    }

    /**
     * 删除领料记录,若已领料，不允许删除
     *
     * @param mcid
     * @return
     */
    public int delMConsume(String mcid) {
        MaterialConsume consume = materialConsumeMapper.selectByPrimaryKey(mcid);
        if (Constant.STRING_THREE.equals(consume.getMcStatus()) || STRING_FOUR.equals(consume.getMcStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return materialConsumeMapper.deleteByPrimaryKey(mcid);
    }

    /**
     * 根据料号查询所有的领料记录
     *
     * @param msn
     * @return
     */
    public List<MaterialConsume> getByMsn(String msn) {
        return materialConsumeMapper.selectByMcMSn(msn);
    }

    /**
     * 根据料号查询所有待领料的总数量
     *
     * @param msn
     * @return
     */
    public int countByMsn(String msn) {
        return materialConsumeMapper.countByMcMSn(msn);
    }

    /**
     * 确认领料记录
     *
     * @return
     */
    public int sureConsume(MaterialConsume consume) {
        MaterialConsume con = materialConsumeMapper.selectByPrimaryKey(consume.getMcId());
        if (Constant.STRING_THREE.equals(con.getMcStatus())) {
            ManPlan manPlan=con.getManPlan();
            PurchaseOrder purchaseOrder=purchaseOrderService.selectByOno(manPlan.getMpOrderId());
            List<BomDetail> details =new ArrayList<> (con.getDetails());
            List<Material> materials = new ArrayList<>(details.size());
            for (BomDetail detail : details) {
                Material material = materialService.checkSnExist(detail.getMaterial().getmSn());
                if(STRING_FOUR.equals(purchaseOrder.getPoStatus())){
                    material.setmLocked(STRING_ONE);
                }
                int i = material.getmCount() - Math.round(detail.getBdNum() * (1 + detail.getBdRate()) * consume.getMcCountIndeed());
                if (i < 0) {
                    return Constant.NUMBER_TOO_BIG;
                }
                material.setmCount(i);
                materials.add(material);
            }
            if(materials.size()==details.size()){
                materials.forEach(material -> {
                    materialService.upMaterial(material);
                });
            }
            consume.setMcStatus(STRING_FOUR);
            consume.setMcDate(KeyGeneratorUtils.dateGenerator());
            consume.setMcOperator(CurrentUser.getCurrentUser().getUname());
            consume.setMcCountIndeed(consume.getMcCountIndeed());
            return materialConsumeMapper.updateByPrimaryKeySelective(consume);
        }
        return Constant.STATUS_NEED_AUDIT;
    }

    /**
     * 根据主键查找领料单
     *
     * @param mcId
     * @return
     */
    public MaterialConsume getConsume(String mcId) {
        return materialConsumeMapper.selectByPrimaryKey(mcId);
    }

}
