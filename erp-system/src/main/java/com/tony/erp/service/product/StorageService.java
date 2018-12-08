package com.tony.erp.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.StorageMapper;
import com.tony.erp.domain.*;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.ManPlanService;
import com.tony.erp.service.OrderService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 11/13/2018 9:55 AM
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class StorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private ManPlanService manPlanService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShipmentService shipmentService;

    /**
     * 获取所有入库记录
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllStos(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Storage> stos=storageMapper.find(null);
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(stos);
        PageInfo<Storage> pageInfo=new PageInfo<>(stos);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    /**
     * 增加产品入库记录
     * @param storage
     * @return
     */
    public int addStorage(Storage storage){
        storage.setStoId(KeyGeneratorUtils.keyUUID());
        storage.setStoStatus(Constant.STRING_ONE);
        if(StringUtils.isEmpty(storage.getStoProCode())){
            return Constant.ARG_NOT_MATCHED;
        }
        return storageMapper.insertSelective(storage);
    }

    /**
     * 更新入库记录
     * @param storage
     * @return
     */
    public int upStorage(Storage storage){
        return storageMapper.updateByPrimaryKeySelective(storage);
    }

    /**
     * 删除入库记录
     * @param sId
     * @return
     */
    public int delStorage(String sId){
        Storage sto=storageMapper.selectByPrimaryKey(sId);
        if(Constant.STRING_THREE.equals(sto.getStoStatus())){
            return Constant.STATUS_CANNOT_CHANGED;
        }
        return storageMapper.deleteByPrimaryKey(sId);
    }

    /**
     * 确认入库数量
     * @param sId  主键
     * @param indeed  实际入库数量
     * @param status 确认状态
     * @return
     */
    public int confirm(String sId,int indeed,String status) {
        Storage sto = storageMapper.selectByPrimaryKey(sId);
        sto.setStoSurer(CurrentUser.getCurrentUser().getUname());
        sto.setStoRealDate(KeyGeneratorUtils.dateGenerator());
        sto.setStoIndeedNum(indeed);
        sto.setStoStatus(status);
        log.info("入库记录确认人为[{}],状态为[{}]",sto.getStoSurer(),sto.getStoStatus());
        if (Constant.STRING_THREE.equals(status)) {
            //根据入库计划编号获取对应生产计划
            ManPlan manPlan = manPlanService.getManPlanByMpSn(sto.getStoMpSn());
            //根据生产计划编号查找对应订单
            Map<String, String> params = new HashMap<>();
            params.put("oNo", manPlan.getMpOrderId());
            Order order = orderService.getByCriteria(params).get(0);
            if (ObjectUtils.isEmpty(order)) {
                return Constant.ARG_NOT_MATCHED;
            }
            //根据产品编号查找对应产品
            Product product = productService.getProduct(sto.getStoProCode());
            product.setProCount(product.getProCount() + indeed);
            if(productService.upProduct(product)<0){
                return Constant.ARG_NOT_MATCHED;
            }
            //更新订单状态为待出货
            if (product.getProCount() >= order.getOCount()&&Constant.STRING_FIVE.equals(order.getOStatus())) {
                Shipment shipment = new Shipment();
                shipment.setSOrderNo(order.getONo());
                shipment.setSProCode(order.getOProductCode());
                shipment.setSShipCount(order.getOCount());
                order.setOStatus(Constant.STRING_THREE);
                int i = orderService.upOrderByShip(order) + shipmentService.addShip(shipment);
                log.info("新增出货记录为[{}]",shipment.toString());
                if (i < 0) {
                    return Constant.ARG_NOT_MATCHED;
                }
            }
            return storageMapper.updateByPrimaryKeySelective(sto);
        }
        return storageMapper.updateByPrimaryKeySelective(sto);
    }

    public Storage getByPrimaryKey(String stoId){
        return storageMapper.selectByPrimaryKey(stoId);
    }

}
