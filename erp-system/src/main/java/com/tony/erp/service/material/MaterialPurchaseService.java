package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.MaterialPurchaseMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.utils.CurrentUser;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialPurchaseService {

    @Autowired
    private MaterialPurchaseMapper materialPurchaseMapper;

    @Autowired
    private MaterialService materialService;

    /**
     * 添加采购记录
     *
     * @param materialPurchase
     * @return
     */
    public int addMPurchase(MaterialPurchase materialPurchase) {
        materialPurchase.setMphId(KeyGeneratorUtils.keyUUID());
        materialPurchase.setMphDate(KeyGeneratorUtils.dateGenerator());
        materialPurchase.setMphOperator(CurrentUser.getCurrentUser().getUname());
        if (StringUtils.isEmpty(materialPurchase.getMphSn())) {
            return -1;
        }
        Material material = materialService.checkSnExist(materialPurchase.getMphSn());
        if (ObjectUtils.isEmpty(material)) {
            Material m = new Material();
            m.setmCount(materialPurchase.getMphCount());
            m.setmName(materialPurchase.getMphName());
            m.setmSn(materialPurchase.getMphSn());
            m.setmNote(materialPurchase.getMphNote());
            return materialPurchaseMapper.insert(materialPurchase) + materialService.addMaterial(m);
        }
        material.setmCount(material.getmCount() + materialPurchase.getMphCount());
        material.setmNote(materialPurchase.getMphNote());
        return materialPurchaseMapper.insert(materialPurchase) + materialService.upMaterial(material);
    }

    /**
     * 更新采购记录
     *
     * @param materialPurchase
     * @return
     */
    public int upMPurchase(MaterialPurchase materialPurchase) {
        if (StringUtils.isEmpty(materialPurchase.getMphSn()) || StringUtils.isEmpty(materialPurchase.getMphId())) {
            return -1;
        }
        MaterialPurchase mp = materialPurchaseMapper.selectByPrimaryKey(materialPurchase.getMphId());
        if (ObjectUtils.isEmpty(materialPurchase)) {
            return -1;
        }
        Material material = materialService.checkSnExist(materialPurchase.getMphSn());
        material.setmCount(material.getmCount() + materialPurchase.getMphCount() - mp.getMphCount());
        return materialPurchaseMapper.updateByPrimaryKeySelective(materialPurchase) + materialService.upMaterial(material);

    }

    /**
     * 获取所有采购记录
     *
     * @return
     */
    public List<MaterialPurchase> getAll(int pageNum) {
        PageHelper.startPage(pageNum, 10);
        return materialPurchaseMapper.selectAll();
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
     *
     * @param mpid
     * @return
     */

    public int delMPurchase(String mpid) {
        if (StringUtils.isEmpty(mpid)) {
            return -1;
        }
        MaterialPurchase materialPurchase = materialPurchaseMapper.selectByPrimaryKey(mpid);
        if (ObjectUtils.isEmpty(materialPurchase)) {
            return -1;
        }
        Material material = materialService.checkSnExist(materialPurchase.getMphSn());
        material.setmCount(material.getmCount() - materialPurchase.getMphCount());
        return materialPurchaseMapper.deleteByPrimaryKey(mpid) + materialService.upMaterial(material);
    }


}
