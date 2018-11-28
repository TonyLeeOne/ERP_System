package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.OrderMapper;
import com.tony.erp.domain.Custom;
import com.tony.erp.domain.ManPlan;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.Shipment;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.product.ShipmentService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private CustomService customService;

    /**
     * 分页查询订单数据
     *
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllOrders(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Order> orders = orderMapper.find(null);
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(orders);
        PageInfo<Order> pageInfo = new PageInfo<>(orders);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(pageInfo.getPageNum());
        return pageHelperEntity;
    }

    /**
     * 添加新订单
     *
     * @param order
     * @return
     */
    public int addOrder(Order order) {
        Custom custom=customService.getCustom(order.getOCustomName());
        if(!ObjectUtils.isEmpty(custom)){
            order.setOStatus(Constant.STRING_ONE);
            order.setOId(KeyGeneratorUtils.keyUUID());
            order.setOCreateDate(KeyGeneratorUtils.dateGenerator());
            order.setOCreator(CurrentUser.getCurrentUser().getUname());
            order.setOStatus(Constant.STRING_ONE);
            order.setOCustomName(custom.getCustomName());
            return orderMapper.insertSelective(order);
        }
       return Constant.ARG_NOT_MATCHED;

    }

    /**
     * 删除订单，已出货订单不可删除
     *
     * @param oid
     * @return
     */
    public int delOrder(String oid) {
        Order order = orderMapper.selectByPrimaryKey(oid);
        if (Constant.STRING_FOUR.equals(order.getOStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return orderMapper.deleteByPrimaryKey(oid);
    }

    /**
     * 审核订单
     *
     * @param oId
     * @param status
     * @param notes
     * @return
     */
    public int confirmOrder(String oId, String status, String notes) {
        Order order = this.getByOid(oId);
        System.out.println(order.toString());
        if (Constant.STRING_ONE.equals(order.getOStatus()) || Constant.STRING_TWO.equals(order.getOStatus()) || Constant.STRING_FIVE.equals(order.getOStatus())) {
            if (order.getOCount() > order.getProduct().getProCount()) {
                order.setOAuditor(CurrentUser.getCurrentUser().getUname());
                order.setOStatus(Constant.STRING_FIVE);
                order.setOAuditDate(KeyGeneratorUtils.dateGenerator());
                order.setONote(Constant.PRO_SHORTAGE);
                return orderMapper.updateByPrimaryKeySelective(order);
            }
            order.setOAuditor(CurrentUser.getCurrentUser().getUname());
            order.setOStatus(status);
            order.setOAuditDate(KeyGeneratorUtils.dateGenerator());
            if(notes!=null){
                order.setONote(notes);
            }
            Shipment shipment = new Shipment();
            shipment.setSOrderNo(order.getONo());
            shipment.setSProCode(order.getOProductCode());
            shipment.setSShipCount(order.getOCount());
            return orderMapper.updateByPrimaryKeySelective(order) + shipmentService.addShip(shipment);
        }
        return Constant.STATUS_CANNOT_CHANGED;
    }


    /**
     * 修改订单信息,订单状态为3,4的，不允许修改
     */
    public int upOrder(Order order) {
        Custom custom=customService.getCustom(order.getOCustomName());
        order.setOCustomName(custom.getCustomName());
        Order exist = this.getByOid(order.getOId());
        if (Constant.STRING_FOUR.equals(exist.getOStatus())||Constant.STRING_THREE.equals(exist.getOStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        order.setOModifier(CurrentUser.getCurrentUser().getUname());
        if (Constant.STRING_TWO.equals(exist.getOStatus())||Constant.STRING_FIVE.equals(exist.getOStatus())) {
            order.setOStatus(Constant.STRING_ONE);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 出货调用
     * 修改订单状态
     */
    public int upOrderByShip(Order order) {
        Order exist = this.getByOid(order.getOId());
        if (Constant.STRING_FOUR.equals(exist.getOStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        order.setOModifier(CurrentUser.getCurrentUser().getUname());
        if (Constant.STRING_TWO.equals(exist.getOStatus())||Constant.STRING_FIVE.equals(exist.getOStatus())) {
            order.setOStatus(Constant.STRING_ONE);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     * 根据主键获取订单信息
     *
     * @param oid
     * @return
     */
    public Order getByOid(String oid) {
        return orderMapper.selectByPrimaryKey(oid);
    }

    /**
     * 根据条件查询订单信息
     *
     * @param params
     * @return
     */
    public List<Order> getByCriteria(Map<String, String> params) {
        return orderMapper.find(params);
    }


    /**
     * 获取进行中的订单总数量
     *
     * @return
     */
    public int getTotal() {
        return orderMapper.getTotal();
    }


    /**
     * 根据订单号，批量删除
     *
     * @param orderNos
     * @return
     */
    public int batchDelete(String[] orderNos) {
        return orderMapper.batchDeleteByOno(orderNos);
    }


    /**
     * 获取所有待出货的状态为订单
     *
     * @return
     */
    public List<String> getONos(String oStatus) {
        return orderMapper.getONos(oStatus);
    }


}
