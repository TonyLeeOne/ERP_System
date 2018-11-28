package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.MaterialConsumeMapper;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialConsume;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

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
//        参数异常-1
        if (StringUtils.isEmpty(consume.getMcMSn())) {
            return Constant.ARG_NOT_MATCHED;
        }
        Material material = materialService.checkSnExist(consume.getMcMSn());
        if (ObjectUtils.isEmpty(material)) {
            return Constant.ARG_NOT_MATCHED;
        }
//        数量不足，不可以创建领料单
        int delta = material.getmCount() - consume.getMcCountNeeded();
        if (delta < 0) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return materialConsumeMapper.insert(consume) + materialService.upMaterial(material);
    }

    /**
     * 更新领料记录
     *
     * @param consume
     * @return
     */
    public int upMConsum(MaterialConsume consume) {
        MaterialConsume mc = materialConsumeMapper.selectByPrimaryKey(consume.getMcId());
        if (StringUtils.isEmpty(consume.getMcMSn())) {
            return Constant.ARG_NOT_MATCHED;
        }
        Material material = materialService.checkSnExist(consume.getMcMSn());
        int delta = material.getmCount() - consume.getMcCountNeeded();
        if (delta < 0) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return materialConsumeMapper.updateByPrimaryKeySelective(consume) + materialService.upMaterial(material);
    }


    /**
     * 审核领料记录
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
        if (Constant.STRING_ONE.equals(consume.getMcStatus())) {
            Material material = materialService.checkSnExist(consume.getMcMSn());
            material.setmCount(material.getmCount() + consume.getMcCountIndeed());
            return materialConsumeMapper.deleteByPrimaryKey(mcid) + materialService.upMaterial(material);
        }
        return Constant.STATUS_CANNOT_CHANGED;
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

     * @return
     */
    public int sureConsume(MaterialConsume consume) {
        MaterialConsume con = materialConsumeMapper.selectByPrimaryKey(consume.getMcId());
        if(Constant.STRING_THREE.equals(con.getMcStatus())) {
            Material material = materialService.checkSnExist(con.getMcMSn());
            material.setmCount(material.getmCount() - con.getMcCountNeeded());
            consume.setMcStatus(Constant.STRING_TWO);
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
