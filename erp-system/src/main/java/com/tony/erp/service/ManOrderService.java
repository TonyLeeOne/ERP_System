package com.tony.erp.service;

import com.tony.erp.constant.Constant;
import com.tony.erp.dao.ManOrderMapper;
import com.tony.erp.domain.ManOrder;
import com.tony.erp.domain.ManPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author jli2
 * @date 11/12/2018 12:01 PM
 **/

@Service
@Transactional(rollbackFor = Exception.class)
public class ManOrderService {

    @Autowired
    private ManOrderMapper manOrderMapper;

    @Autowired
    private ManPlanService manPlanService;

    /**
     * 添加新的工单
     * @param manOrder
     * @return
     */
    public int addManOrder(ManOrder manOrder){
        if(ObjectUtils.isEmpty(manOrder.getMoWaitCount())||StringUtils.isEmpty(manOrder.getMoMpSn())){
            return Constant.ARG_NOT_MATCHED;
        }
        ManPlan manPlan=manPlanService.getManPlanByMpSn(manOrder.getMoMpSn());
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        manPlan.setMpCount(manPlan.getMpCount()+manOrder.getMoWaitCount());
        manPlan.setMpStatus(Constant.STRING_ONE);
        return manOrderMapper.insert(manOrder);
    }

    /**
     * 修改工单信息
     * @param manOrder
     * @return
     */
    public int upManOrder(ManOrder manOrder){
        ManPlan manPlan=manPlanService.getManPlanByMpSn(manOrder.getMoMpSn());
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        ManOrder mo=manOrderMapper.selectByPrimaryKey(manOrder.getMoId());
        manPlan.setMpCount(manPlan.getMpCount()-mo.getMoCount()+manOrder.getMoCount());
        if(manOrder.getMoCount().equals(manOrder.getMoWaitCount())){
            manOrder.setMoStatus(Constant.STRING_TWO);
        }
        List<String> orderStatus=manOrderMapper.selectStatusByMpSn(manPlan.getMpSn());
        if(!orderStatus.contains(Constant.STRING_ONE)){
            manPlan.setMpStatus(Constant.STRING_TWO);
        }
        return manOrderMapper.updateByPrimaryKeySelective(manOrder)+manPlanService.upManPlan(manPlan);
    }

    /**
     * 根据主键删除工单记录
     * @param mid
     * @return
     */
    public int delMapper(String mid){
        ManOrder manOrder=manOrderMapper.selectByPrimaryKey(mid);
        if(Constant.STRING_TWO.equals(manOrder.getMoStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        ManPlan manPlan=manPlanService.getManPlanByMpSn(manOrder.getMoMpSn());
        manPlan.setMpCount(manPlan.getMpCount()-manOrder.getMoCount());
        List<String> orderStatus=manOrderMapper.selectStatusByMpSn(manPlan.getMpSn());
        if(!orderStatus.contains(Constant.STRING_ONE)){
            manPlan.setMpStatus(Constant.STRING_TWO);
        }
        return manPlanService.upManPlan(manPlan)+manOrderMapper.deleteByPrimaryKey(mid);
    }


    /**
     * 获取所有正在进行的生产工单
     * @return
     */
    public int getTotal(){
        return manOrderMapper.getTotal();
    }

}
