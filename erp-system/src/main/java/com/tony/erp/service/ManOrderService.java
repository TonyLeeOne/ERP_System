package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.ManOrderMapper;
import com.tony.erp.domain.ManOrder;
import com.tony.erp.domain.ManPlan;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.apache.ibatis.executor.keygen.KeyGenerator;
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
        if(ObjectUtils.isEmpty(manOrder.getMoCount())||StringUtils.isEmpty(manOrder.getMoMpSn())){
            return Constant.ARG_NOT_MATCHED;
        }
        ManPlan manPlan=manPlanService.getManPlanByMpSn(manOrder.getMoMpSn());
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        int delta=manPlan.getMpCount()-manOrder.getMoCount();
        if(delta<0){
            return Constant.ARG_NOT_MATCHED;
        }
        manPlan.setMpCount(delta);
        manOrder.setMoWaitCount(0);
        manOrder.setMoId(KeyGeneratorUtils.keyUUID());
        manOrder.setMoStatus(Constant.STRING_ONE);
        return manOrderMapper.insert(manOrder)+manPlanService.upManPlan(manPlan);
    }

    /**
     * 修改工单信息
     * @param manOrder
     * @return
     */
    public int upManOrder(ManOrder manOrder){
        ManPlan manPlan=manPlanService.getManPlanByMpSn(manOrder.getMoMpSn());
        ManOrder mo=manOrderMapper.selectByPrimaryKey(manOrder.getMoId());
        if(Constant.STRING_TWO.equals(manPlan.getMpStatus())||Constant.STRING_TWO.equals(mo.getMoStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        int delta=manPlan.getMpCount()+mo.getMoCount()-manOrder.getMoCount();
        int numDelta=manOrder.getMoCount()-manOrder.getMoWaitCount();
        if(delta<0||numDelta<0){
            return Constant.ARG_NOT_MATCHED;
        }
        if(manOrder.getMoCount().equals(manOrder.getMoWaitCount())){
            manOrder.setMoStatus(Constant.STRING_TWO);
        }
        manOrderMapper.updateByPrimaryKeySelective(manOrder);
        manPlan.setMpCount(delta);
        List<String> orderStatus=manOrderMapper.selectStatusByMpSn(manPlan.getMpSn());
        if(!orderStatus.contains(Constant.STRING_ONE)&&(manPlan.getMpCount()==0)){
            manPlan.setMpStatus(Constant.STRING_TWO);
        }
        return manPlanService.upManPlan(manPlan);
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
        manPlan.setMpCount(manPlan.getMpCount()+manOrder.getMoCount());
        List<String> orderStatus=manOrderMapper.selectStatusByMpSn(manPlan.getMpSn());
        if(!orderStatus.contains(Constant.STRING_ONE)&&(manPlan.getMpCount()==0)){
            manPlan.setMpStatus(Constant.STRING_TWO);
        }
        return manOrderMapper.deleteByPrimaryKey(mid)+manPlanService.upManPlan(manPlan);
    }


    /**
     * 获取所有正在进行的生产工单
     * @return
     */
    public int getTotal(){
        return manOrderMapper.getTotal();
    }


    /**
     * 分页查询所有的工单记录
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllManOrders(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<ManOrder> orders=manOrderMapper.getAllManOrders();
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(orders);
        PageInfo pageInfo=new PageInfo(orders);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(pageInfo.getPageNum());
        return pageHelperEntity;
    }


    /**
     * 根据主键查找生产工单
     * @param moId
     * @return
     */
    public ManOrder getManOrder(String moId){
        return manOrderMapper.selectByPrimaryKey(moId);
    }


    /**
     * 根据订单计划产找所有工单
     * @param mpsn
     * @return
     */
    public List<ManOrder> selectByMpSn(String mpsn){
        return manOrderMapper.selectByMpSn(mpsn);
    }

    /**
     * 查找所有的转态为1的工单编号
     * @return
     */
    public List<String> selectAllMoSn(){
        return manOrderMapper.selectAllMoSn();
    }

    /**
     * 根据工单号查找工单信息
     * @param moSn
     * @return
     */
    public ManOrder selectByMoSn(String moSn){
        return manOrderMapper.selectByMoSn(moSn);
    }
}
