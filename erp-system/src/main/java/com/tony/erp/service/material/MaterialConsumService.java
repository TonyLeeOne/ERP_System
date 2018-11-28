package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.MaterialConsumeMapper;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialConsume;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
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
public class MaterialConsumService {

    @Autowired
    private MaterialConsumeMapper materialConsumeMapper;

    @Autowired
    private MaterialService materialService;

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
        consume.setMcDate(KeyGeneratorUtils.dateGenerator());
        consume.setMcOperator(CurrentUser.getCurrentUser().getUname());
        consume.setMcStatus(Constant.STRING_ONE);
//        参数异常-1
        if (StringUtils.isEmpty(consume.getMcMSn())) {
            return Constant.ARG_NOT_MATCHED;
        }
        Material material = materialService.checkSnExist(consume.getMcMSn());
        if (ObjectUtils.isEmpty(material)) {
            return Constant.ARG_NOT_MATCHED;
        }
//        数量不足，更改物料表状态和领料表状态
        int delta = material.getmCount() - consume.getMcCountIndeed();
        if (delta < 0) {
            material.setmStatus(Constant.STRING_TWO);
            materialService.upMaterial(material);
            return Constant.STATUS_CANNOT_CHANGED;

        }
        material.setmCount(delta);
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
        int delta = material.getmCount() - consume.getMcCountIndeed() + mc.getMcCountIndeed();
        if (delta < 0) {
            material.setmStatus(Constant.STRING_TWO);
            materialService.upMaterial(material);
            return Constant.STATUS_CANNOT_CHANGED;
        }
        material.setmCount(delta);
        return materialConsumeMapper.updateByPrimaryKeySelective(consume) + materialService.upMaterial(material);
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
     *
     * @param mcId   主键
     * @param indeed 实际领料数量
     * @return
     */
    public int sureConsume(String mcId, int indeed) {
        MaterialConsume consume = materialConsumeMapper.selectByPrimaryKey(mcId);
        consume.setMcStatus(Constant.STRING_TWO);
        consume.setMcDate(KeyGeneratorUtils.dateGenerator());
        consume.setMcOperator(CurrentUser.getCurrentUser().getUname());
        consume.setMcCountIndeed(indeed);
        return materialConsumeMapper.updateByPrimaryKeySelective(consume);
    }

}
