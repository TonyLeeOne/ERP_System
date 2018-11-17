package com.tony.erp.service.material;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.domain.pagehelper.PageHelperEntity;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    public Material checkSnExist(String msn){
        return materialMapper.checkBySn(msn);
    }

    public PageHelperEntity getAllMaterials(int pageNum){
        PageHelper.startPage(pageNum,10);
        List<Material> materials=materialMapper.getAllMaterials();
        PageHelperEntity pageHelperEntity=new PageHelperEntity();
        pageHelperEntity.setRows(materials);
        PageInfo<Material> pageInfo=new PageInfo<>(materials);
        pageHelperEntity.setTotal(pageInfo.getTotal());
        return pageHelperEntity;
    }


    public int upMaterial(Material material){
        return materialMapper.updateByPrimaryKeySelective(material);
    }

    public int addMaterial(Material material){
        material.setmId(KeyGeneratorUtils.keyUUID());
        return materialMapper.insert(material);
    }

}
