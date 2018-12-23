package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.regexp.internal.RE;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.MaterialPurchaseMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.domain.Order;
import com.tony.erp.domain.PurchaseOrder;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.service.OrderService;
import com.tony.erp.service.PurchaseOrderService;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tony.erp.constant.Constant.STRING_FIVE;
import static com.tony.erp.constant.Constant.STRING_FOUR;
import static com.tony.erp.constant.Constant.STRING_TWO;

/**
 * @author jli2
 * @date  2018/11/12
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialPurchaseService {

    @Autowired
    private MaterialPurchaseMapper materialPurchaseMapper;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private OrderService orderService;

    /**
     * 添加采购记录
     * @param materialPurchase
     * @return
     */
    public int addMPurchase(MaterialPurchase materialPurchase) {
        Material material=materialService.checkSnExist(materialPurchase.getMphSn());
        materialPurchase.setMphId(KeyGeneratorUtils.keyUUID());
        materialPurchase.setMphName(material.getmName());
        return materialPurchaseMapper.insertSelective(materialPurchase);
    }

    /**
     * 更新采购记录
     * @param materialPurchase
     * @return
     */
    public int upMPurchase(MaterialPurchase materialPurchase) {
        return materialPurchaseMapper.updateByPrimaryKeySelective(materialPurchase);
    }

    /**
     * 获取所有采购记录
     * @return
     */
    public PageHelperEntity getAll(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        List<MaterialPurchase> purchases=materialPurchaseMapper.selectAll();
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(purchases);
        PageInfo<MaterialPurchase> pageInfo=new PageInfo<>(purchases);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }

    /**
     * 根据mphSn查找所有采购记录
     * @param mphSn 料号
     * @return
     */
    public List<MaterialPurchase> getByMphSn(String mphSn) {
        return materialPurchaseMapper.selectByMphSn(mphSn);
    }

    /**
     * 根据主键删除采购记录
     * @param mpid
     * @return
     */
    public int delMPurchase(String mpid) {
        MaterialPurchase materialPurchase = materialPurchaseMapper.selectByPrimaryKey(mpid);
        if(ObjectUtils.isEmpty(materialPurchase)||materialPurchase.getMphStatus().equals(STRING_TWO)){
            return Constant.ARG_NOT_MATCHED;
        }
        return materialPurchaseMapper.deleteByPrimaryKey(mpid);
    }

    /**
     * 根据主键查找采购记录
     * @param mpId
     * @return
     */
    public MaterialPurchase getMaterialPurchase(String mpId){
        return materialPurchaseMapper.selectByPrimaryKey(mpId);
    }

    /**
     * 审核采购申请
     * @param purchase
     * @return
     */
    public int audit(MaterialPurchase purchase){
        purchase.setMphSender(CurrentUser.getCurrentUser().getUname());
        return materialPurchaseMapper.updateByPrimaryKeySelective(purchase);
    }

    /**
     * 确认采购入库
     * @param purchase
     * @return
     */
    public int confirm(MaterialPurchase purchase){
        MaterialPurchase materialPurchase=materialPurchaseMapper.selectByPrimaryKey(purchase.getMphId());
        purchase.setMphOperator(CurrentUser.getCurrentUser().getUname());
        purchase.setMphDate(KeyGeneratorUtils.dateGenerator());
        purchase.setMphStatus(STRING_TWO);
        Material material=materialService.checkSnExist(materialPurchase.getMphSn());
        material.setmCount(material.getmCount()+materialPurchase.getMphCount());
        materialPurchaseMapper.updateByPrimaryKeySelective(purchase);
        materialService.upMaterial(material);
        String status=materialPurchaseMapper.selectStatusByMpoId(materialPurchase.getMphPoId()).toString();
        PurchaseOrder purchaseOrder=purchaseOrderService.getPoByPrimaryKey(materialPurchase.getMphPoId());
        if(!status.contains(Constant.STRING_ONE)){
            Map<String,String> map=new HashMap<>(1);
            map.put("oNo",purchaseOrder.getPoOno());
            Order order=orderService.getByCriteria(map).get(0);
            order.setOStatus(STRING_FIVE);
            System.out.println(order.toString());
            purchaseOrder.setPoStatus(Constant.STRING_FOUR);
            return purchaseOrderService.updatePo(purchaseOrder)+orderService.upOrderByPurchase(order);
        }
        return Constant.OK;
    }

}
