package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.ManPlanMapper;
import com.tony.erp.domain.ManPlan;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 11/12/2018 10:03 AM
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class ManPlanService {

    @Autowired
    private ManPlanMapper manPlanMapper;

    /**
     * 添加生产计划
     * @param manPlan
     * @return
     */
    public int addManPlan(ManPlan manPlan){
        manPlan.setMpId(KeyGeneratorUtils.keyUUID());
        manPlan.setMpStatus(Constant.STRING_ONE);
        return manPlanMapper.insert(manPlan);
    }

    /**
     * 修改生产计划信息，状态为2不可修改
     * @param manPlan
     * @return
     */
    public int upManPlan(ManPlan manPlan){
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return manPlanMapper.updateByPrimaryKeySelective(manPlan);
    }

    /**
     * 根据主键mpId删除生产计划
     * @param mpId
     * @return
     */
    public int delManPlan(String mpId){
        ManPlan manPlan=manPlanMapper.selectByPrimaryKey(mpId);
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return manPlanMapper.deleteByPrimaryKey(mpId);
    }

    /**
     * 分页查询所有生产计划
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAll(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<ManPlan> plans=manPlanMapper.find(null);
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(plans);
        PageInfo<ManPlan> pageInfo=new PageInfo<>(plans);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(),10));
        return pageHelperEntity;
    }

    /**
     * 通过mpsn查找生产计划
     * @param mpsn
     * @return
     */
    public ManPlan getManPlanByMpSn(String mpsn){
        Map<String,String> params=new HashMap<>();
        params.put("mpSn",mpsn);
        List<ManPlan> plans=manPlanMapper.find(params);
        if(CollectionUtils.isEmpty(plans)){
            return null;
        }
        return (ManPlan) manPlanMapper.find(params).get(0);
    }

    /**
     * 获取所有在进行中的生产计划数量
     * @return
     */
    public int getTotal(){
        return manPlanMapper.getTotal();
    }

}
