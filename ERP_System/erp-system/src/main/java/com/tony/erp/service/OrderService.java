package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.OrderMapper;
import com.tony.erp.domain.*;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.material.MaterialPurchaseService;
import com.tony.erp.service.product.ShipmentService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private BomService bomService;

    @Autowired
    private DetailService detailService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private MaterialPurchaseService purchaseService;

    /**
     * 分页查询订单数据
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
     * @param oId
     * @param status
     * @param notes
     * @return
     */
    public int confirmOrder(String oId, String status, String notes) {
        int res=0;
        Order order = this.getByOid(oId);
        order.setOAuditor(CurrentUser.getCurrentUser().getUname());
        order.setOAuditDate(KeyGeneratorUtils.dateGenerator());
        if(notes!=null){
            order.setONote(notes);
        }
        if (Constant.STRING_ONE.equals(order.getOStatus()) || Constant.STRING_TWO.equals(order.getOStatus())) {
            order.setOStatus(status);
            if(Constant.STRING_TWO.equals(status)){
                return orderMapper.updateByPrimaryKeySelective(order);
            }
            //订单数量大于库存数量
            if (order.getOCount() > order.getProduct().getProCount()) {
                BOM bom=bomService.selectByPCode(order.getOProductCode());
                //存库物料不足，创建采购订单,获取当前产品的所有bom清单
                if(ObjectUtils.isEmpty(bom)){
                    return Constant.ARG_NOT_MATCHED;
                }
                List<BomDetail> details=detailService.selectByBCode(bom.getBCode());
                if(CollectionUtils.isEmpty(details)){
                    return Constant.ARG_NOT_MATCHED;
                }
                List<MaterialPurchase> purchaseList=new ArrayList<>(details.size());
                for (BomDetail d :details) {
                    int needed=(int)Math.ceil(d.getBdNum()*(1+d.getBdRate())*order.getOCount());
                    if(d.getMaterial().getmCount()<needed){
                        res++;
                    }
                    MaterialPurchase materialPurchase=new MaterialPurchase();
                    materialPurchase.setMphCount(needed);
                    materialPurchase.setMphSn(d.getMaterial().getmSn());
                    materialPurchase.setMphPrice(d.getMaterial().getmPrice());
                    purchaseList.add(materialPurchase);
                }
                if(res>0){
                    PurchaseOrder purchaseOrder=new PurchaseOrder();
                    purchaseOrder.setPoId(KeyGeneratorUtils.keyUUID());
                    purchaseOrder.setPoCdate(KeyGeneratorUtils.dateGenerator());
                    purchaseOrder.setPoCount(order.getOCount());
                    purchaseOrder.setPoBcode(bom.getBCode());
                    purchaseOrder.setPoOno(order.getONo());
                    order.setOStatus(Constant.STRING_SIX);
                    purchaseList.forEach(purchase -> {
                        purchase.setMphPoId(purchaseOrder.getPoId());
                        purchaseService.addMPurchase(purchase);
                    });
                    return purchaseOrderService.addPo(purchaseOrder)+orderMapper.updateByPrimaryKeySelective(order);
                }
                order.setOStatus(Constant.STRING_FIVE);
                order.setONote(order.getONote()+Constant.PRO_SHORTAGE);
                return orderMapper.updateByPrimaryKeySelective(order);
            }
            //库存产品充足，创建出货记录，并更新订单状态
            Shipment shipment = new Shipment();
            shipment.setSOrderNo(order.getONo());
            shipment.setSProCode(order.getOProductCode());
            shipment.setSShipCount(order.getOCount());
            return orderMapper.updateByPrimaryKeySelective(order) + shipmentService.addShip(shipment);
        }
        return Constant.STATUS_CANNOT_CHANGED;
    }


    /**
     * 修改订单信息,订单状态为3,4,5的，不允许修改
     */
    public int upOrder(Order order) {
        Custom custom=customService.getCustom(order.getOCustomName());
        if(!ObjectUtils.isEmpty(custom)){
            order.setOCustomName(custom.getCustomName());
        }
        Order exist = this.getByOid(order.getOId());
        if (Constant.STRING_FOUR.equals(exist.getOStatus())||Constant.STRING_THREE.equals(exist.getOStatus())||Constant.STRING_SIX.equals(exist.getOStatus())||Constant.STRING_FIVE.equals(exist.getOStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        order.setOModifier(CurrentUser.getCurrentUser().getUname());
        if (Constant.STRING_TWO.equals(exist.getOStatus())) {
            order.setOStatus(Constant.STRING_ONE);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 修改订单信息状态为生产5
     */
    public int upOrderByPurchase(Order order) {
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
        if (Constant.STRING_TWO.equals(exist.getOStatus())) {
            order.setOStatus(Constant.STRING_ONE);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }


    /**
     * 根据主键获取订单信息
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
     * 检查订单标号是否被占用
     * @param oNo
     * @return
     */
    public boolean checkOnoExists(String oNo){
        Map<String,String> map=new HashMap<>();
        map.put("oNo",oNo);
        return !CollectionUtils.isEmpty(getByCriteria(map));
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
     * @param orderNos
     * @return
     */
    public int batchDelete(String[] orderNos) {
        return orderMapper.batchDeleteByOno(orderNos);
    }


    /**
     * 获取所有待出货状态的订单
     * @return
     */
    public List<String> getONos(String oStatus) {
        return orderMapper.getONos(oStatus);
    }


    /**
     * 统计数据
     * @return
     */
    public List<String> dataCollection(){
        return orderMapper.dataCollection();
    }
}
