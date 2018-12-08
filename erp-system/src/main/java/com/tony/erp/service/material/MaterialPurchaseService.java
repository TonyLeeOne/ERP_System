package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.constant.Constant;
import com.tony.erp.dao.MaterialPurchaseMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;
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

    /**
     * 添加采购记录
     * @param materialPurchase
     * @return
     */
    public int addMPurchase(MaterialPurchase materialPurchase) {
        materialPurchase.setMphId(KeyGeneratorUtils.keyUUID());
        String mph=materialPurchase.getMphSn();
        materialPurchase.setMphName(mph.substring(0,mph.indexOf("(")));
        materialPurchase.setMphSn(mph.substring(mph.indexOf("(")+1,mph.indexOf(")")));
        materialPurchase.setMphVendorId(Constant.STRING_ONE);
        System.out.println(materialPurchase.toString());
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
     *
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
     *
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
        if (ObjectUtils.isEmpty(materialPurchase)) {
            return -1;
        }
        if(Constant.STRING_FOUR.equals(materialPurchase.getMphVendorId())||Constant.STRING_THREE.equals(materialPurchase.getMphVendorId())){
            return Constant.STATUS_CANNOT_CHANGED;
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
        purchase.setMphOperator(CurrentUser.getCurrentUser().getUname());
        purchase.setMphDate(KeyGeneratorUtils.dateGenerator());
        return materialPurchaseMapper.updateByPrimaryKeySelective(purchase);
    }

}
