package com.tony.erp.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.PurchaseOrderMapper;
import com.tony.erp.domain.*;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.material.MaterialPurchaseService;
import com.tony.erp.service.material.MaterialService;
import com.tony.erp.service.product.ProductService;
import com.tony.erp.utils.KeyGeneratorUtils;
import com.tony.erp.utils.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import static com.tony.erp.constant.Constant.*;

/**
 * @author jli2
 * @date 12/17/2018 7:21 PM
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;

    @Autowired
    private MaterialPurchaseService materialPurchaseService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private DetailService detailService;

    public int addPo(PurchaseOrder order) {
        order.setPoStatus(STRING_ONE);
        order.setPoCdate(KeyGeneratorUtils.dateGenerator());
        return purchaseOrderMapper.insertSelective(order);
    }

    public int updatePo(PurchaseOrder order) {
        return purchaseOrderMapper.updateByPrimaryKeySelective(order);
    }

    public int delPo(String poId) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(poId);
        Set<MaterialPurchase> purchases = purchaseOrder.getPurchases();
        if (purchases.size() > 0 && !purchases.isEmpty()) {
            for (MaterialPurchase purchase : purchases) {
                if (materialPurchaseService.delMPurchase(purchase.getMphId()) < 1) {
                    return Constant.ARG_NOT_MATCHED;
                }
            }
        }
        return purchaseOrderMapper.deleteByPrimaryKey(poId);
    }

    public PageHelperEntity getAll(int pageNum) {
        Integer pageSize = 10;
        PageHelper.startPage(pageNum, pageSize);
        List<PurchaseOrder> orders = purchaseOrderMapper.selectAll();
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(orders);
        pageHelperEntity.setCurrentPage(pageNum);
        PageInfo<PurchaseOrder> pageInfo = new PageInfo(orders);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        pageHelperEntity.setPageNum(ListUtils.getPageNum(pageInfo.getTotal(), pageSize));
        return pageHelperEntity;
    }

    public PurchaseOrder getPoByPrimaryKey(String poId) {
        return purchaseOrderMapper.selectByPrimaryKey(poId);
    }

    /**
     * 去库存，计算需购买的物料数量
     * @param poId
     * @return
     */
    public int calculate(String poId) {
        PurchaseOrder purchaseOrder = purchaseOrderMapper.selectByPrimaryKey(poId);
//查找对应的订单是否存在
        if (orderService.checkOnoExists(purchaseOrder.getPoOno())) {
            Map<String, String> maps = new HashMap<>(1);
            maps.put("oNo", purchaseOrder.getPoOno());
//            根据订单号查找订单
            Order order = orderService.getByCriteria(maps).get(0);
//            根据订单查找对应产品
            Product product = productService.getProduct(order.getOProductCode());
//            产品是否被去重
            if (!Constant.STRING_TWO.equals(product.getProLocked())) {
//                去产品库存
                int delta = order.getOCount() - order.getProduct().getProCount();
                Set<MaterialPurchase> purchases = purchaseOrder.getPurchases();
                if (CollectionUtils.isEmpty(purchases)) {
                    return ARG_NOT_MATCHED;
                }
//                获取bom清单
                List<BomDetail> details = detailService.selectByBCode(purchaseOrder.getPoBcode());
                if (CollectionUtils.isEmpty(details)) {
                    return ARG_NOT_MATCHED;
                }

//                删除所有采购记录
                for (MaterialPurchase p : purchases) {
                    materialPurchaseService.delMPurchase(p.getMphId());
                }

                details.forEach(bomDetail -> {
                    int needed = 0;
                    Material material = materialService.checkSnExist(bomDetail.getBdMsn());
                    if (!STRING_TWO.equals(material.getmLocked())) {
                        needed = (int) Math.ceil(bomDetail.getBdNum() * (1 + bomDetail.getBdRate()) * delta) - material.getmCount();
                        material.setmLocked(STRING_TWO);
                    } else {
                        needed = (int) Math.ceil(bomDetail.getBdNum() * (1 + bomDetail.getBdRate()) * delta);
                    }
                    if (needed > 0) {
                        MaterialPurchase materialPurchase = new MaterialPurchase();
                        materialPurchase.setMphCount(needed);
                        materialPurchase.setMphSn(bomDetail.getMaterial().getmSn());
                        materialPurchase.setMphName(bomDetail.getMaterial().getmName());
                        materialPurchase.setMphPrice(bomDetail.getMaterial().getmPrice());
                        materialPurchase.setMphPoId(purchaseOrder.getPoId());
                        materialPurchaseService.addMPurchase(materialPurchase);
                    }
                    materialService.upMaterial(material);
                });
//                更新采购订单产品数量
                purchaseOrder.setPoCount(delta);
//                标记产品为锁定状态
                product.setProLocked(STRING_TWO);
//                更新采购订单和产品信息
                return purchaseOrderMapper.updateByPrimaryKeySelective(purchaseOrder) + productService.upProduct(product);
            }
//            当前产品不可重复去库存
            return ALREADY_PURGE_STORAGE;
        }
//        查找的订单信息不存在，抛参数异常
        return ARG_NOT_MATCHED;
    }

    /**
     * 根据订单号查找采购订单
     * @param poOno
     * @return
     */
    public PurchaseOrder selectByOno(String poOno){
        return Optional.ofNullable(purchaseOrderMapper.selectByOno(poOno).get(0)).orElse(null);
    }
}
