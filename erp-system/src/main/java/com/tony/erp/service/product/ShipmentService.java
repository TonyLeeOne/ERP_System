package com.tony.erp.service.product;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.ShipmentMapper;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.Product;
import com.tony.erp.domain.Shipment;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.OrderService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jli2
 * @date 11/12/2018 4:01 PM
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class ShipmentService {

    @Autowired
    private ShipmentMapper shipmentMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    /**
     * 分页查找所有出货记录
     *
     * @param pageNum
     * @return
     */
    public PageHelperEntity getAllShips(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<Shipment> shipments = shipmentMapper.find(null);
        PageHelperEntity pageHelperEntity = new PageHelperEntity();
        pageHelperEntity.setRows(shipments);
        PageInfo<Shipment> pageInfo = new PageInfo<>(shipments);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    /**
     * 添加新的出货记录
     *
     * @param shipment
     * @return
     */
    public int addShip(Shipment shipment) {
        shipment.setSId(KeyGeneratorUtils.keyUUID());
        shipment.setSStatus(Constant.STRING_ONE);
        if (StringUtils.isEmpty(shipment.getSOrderNo())) {
            return Constant.ARG_NOT_MATCHED;
        }
        if (shipment.getSShipCount() > 0) {
            Product product = productService.getProduct(shipment.getSProCode());
            if (ObjectUtils.isEmpty(product)) {
                return Constant.ARG_NOT_MATCHED;
            }
            int delta = product.getProCount() - shipment.getSShipCount();
            if (delta < 0) {
                return Constant.STATUS_CANNOT_CHANGED;
            }
            product.setProCount(delta);
            return shipmentMapper.insertSelective(shipment) + productService.upProduct(product);
        }
        return Constant.ARG_NOT_MATCHED;
    }

    /**
     * 更新出货记录
     *
     * @param shipment
     * @return
     */
    public int upShip(Shipment shipment) {
        if (ObjectUtils.isEmpty(shipment.getSShipCount()) || ObjectUtils.isEmpty(shipment.getSProCode())) {
            return Constant.ARG_NOT_MATCHED;
        }
        Shipment shipment1 = shipmentMapper.selectByPrimaryKey(shipment.getSId());
        if (!Constant.STRING_FOUR.equals(shipment1.getSStatus())) {
            if (!shipment.getSProCode().equals(shipment1.getSProCode())) {
                return Constant.ARG_NOT_MATCHED;
            }
            Product product = productService.getProduct(shipment.getSProCode());
            int delta = product.getProCount() - shipment.getSShipCount() + shipment1.getSShipCount();
            if (delta < 0) {
                return Constant.STATUS_CANNOT_CHANGED;
            }
            product.setProCount(delta);
            return shipmentMapper.updateByPrimaryKeySelective(shipment) + productService.upProduct(product);
        }
        return Constant.STATUS_CANNOT_CHANGED;
    }

    /**
     * 删除出货记录
     *
     * @param sid
     * @return
     */
    public int delShip(String sid) {
        Shipment shipment = shipmentMapper.selectByPrimaryKey(sid);
        if (Constant.STRING_FOUR.equals(shipment.getSStatus())) {
            return Constant.STATUS_CANNOT_CHANGED;
        }
        Product product = productService.getProduct(shipment.getSProCode());
        product.setProCount(product.getProCount() + shipment.getSShipCount());
        return shipmentMapper.deleteByPrimaryKey(sid) + productService.upProduct(product);
    }


    /**
     * 根据成品字段查找所有出货记录
     *
     * @param sProCode
     * @return
     */
    public List<Shipment> getByKey(String column, String sProCode) {
        Map<String, String> paras = new HashMap<>(1);
        paras.put(column, sProCode);
        return shipmentMapper.find(paras);
    }


    /**
     * 出货记录审核
     *
     * @param sId
     * @param status
     * @return
     */
    public int audit(String sId, String status) {
        Shipment shipment = shipmentMapper.selectByPrimaryKey(sId);
        if (Constant.STRING_ONE.equals(shipment.getSStatus()) || Constant.STRING_TWO.equals(shipment.getSStatus())) {
            shipment.setSAuditor(CurrentUser.getCurrentUser().getUname());
            shipment.setSAuditDate(KeyGeneratorUtils.dateGenerator());
            shipment.setSStatus(status);
            log.info("出货审核[{}],审核结果为[{}]", shipment.getSAuditor(), shipment.getSStatus());
            return shipmentMapper.updateByPrimaryKeySelective(shipment);
        }
        return Constant.ARG_NOT_MATCHED;
    }

    /**
     * 确认出货
     *
     * @param shipment
     * @return
     */
    public int confirm(Shipment shipment) {
        Shipment shipment1 = shipmentMapper.selectByPrimaryKey(shipment.getSId());
        if (Constant.STRING_THREE.equals(shipment1.getSStatus())) {
            shipment1.setSSurer(CurrentUser.getCurrentUser().getUname());
            shipment1.setSShipDate(KeyGeneratorUtils.dateGenerator());
            shipment1.setSStatus(Constant.STRING_FOUR);
            Map<String, String> map = new HashMap<>(1);
            map.put("oNo", shipment1.getSOrderNo());
            List<Order> orders = orderService.getByCriteria(map);
            if (CollectionUtils.isEmpty(orders)) {
                return Constant.ARG_NOT_MATCHED;
            }
            Order order = orders.get(0);
            if(order.getOIndeedCount()!=null&&0<order.getOIndeedCount()){
                log.info("已有订单数量[{}]",order.getOIndeedCount());
                order.setOIndeedCount(order.getOIndeedCount()+shipment1.getSShipCount());
            }else {
                order.setOIndeedCount(shipment1.getSShipCount());
            }
            order.setOModifier(shipment1.getSSurer());
            order.setOShipmentDate(shipment1.getSShipDate());
            if(order.getOIndeedCount().equals(order.getOCount())){
                order.setOStatus(Constant.STRING_FOUR);
            }
            log.info("订单信息：" + order.toString());
            log.info("确认出货人[{}],出货订单号为[{}]", shipment1.getSSurer(), shipment1.getSOrderNo());
            return shipmentMapper.updateByPrimaryKeySelective(shipment1) + orderService.upOrder(order);
        }
        return Constant.STATUS_NEED_AUDIT;

    }


    public Shipment getShipByPrimaryKey(String sId) {
        return shipmentMapper.selectByPrimaryKey(sId);
    }

    public List<Shipment> getByCriteria(Map<String,String> maps){
        return shipmentMapper.find(maps);
    }

}
