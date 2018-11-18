package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.OrderMapper;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 分页查询订单数据
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllOrders(int pageNum){
        List<Order> orders= orderMapper.find(null);
        PageHelper.startPage(pageNum,10);
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(orders);
        PageInfo<Order> pageInfo=new PageInfo<>(orders);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(),10));
        return pageHelperEntity;
    }

    /**
     * 添加新订单
     * @param order
     * @return
     */
    public int addOrder(Order order){
        System.out.println(order.toString());
        order.setOStatus(Constant.STRING_ONE);
        order.setOId(KeyGeneratorUtils.keyUUID());
        order.setOCreateDate(KeyGeneratorUtils.dateGenerator());
        order.setOCreator(CurrentUser.getCurrentUser().getUname());
        order.setOStatus(Constant.STRING_ONE);
        return orderMapper.insertSelective(order);
    }

    /**
     * 删除订单，已出货订单不可删除
     * @param oid
     * @return
     */
    public int delOrder(String oid){
        Order order=orderMapper.selectByPrimaryKey(oid);
        if(Constant.STRING_FOUR.equals(order.getOStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return orderMapper.deleteByPrimaryKey(oid);
    }

    /**
     * 审核订单
     * @param oId
     * @param status
     * @param notes
     * @return
     */
    public int confirmOrder(String oId,String status,String notes){
        Order order=this.getByOid(oId);
        if(Constant.STRING_ONE.equals(order.getOStatus())||Constant.STRING_TWO.equals(order.getOStatus())){
            order.setOAuditor(CurrentUser.getCurrentUser().getUname());
            order.setOStatus(status);
            order.setOAuditDate(KeyGeneratorUtils.dateGenerator());
            return orderMapper.updateByPrimaryKeySelective(order);
        }
        return Constant.STATUS_CANNOT_CHANGED;
    }


    /**
     * 修改订单信息
     */
    public int upOrder(Order order){
        if(Constant.STRING_THREE.equals(order.getOStatus())||Constant.STRING_FOUR.equals(order.getOStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        order.setOModifier(CurrentUser.getCurrentUser().getUname());
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     * 根据主键获取订单信息
     * @param oid
     * @return
     */
    public Order getByOid(String oid){
        return orderMapper.selectByPrimaryKey(oid);
    }

    /**
     * 根据条件查询订单信息
     * @param params
     * @return
     */
    public List<Order> getByCriteria(Map<String,String> params){
        return orderMapper.find(params);
    }


    /**
     * 获取进行中的订单总数量
     * @return
     */
    public int getTotal(){
        return orderMapper.getTotal();
    }

}
