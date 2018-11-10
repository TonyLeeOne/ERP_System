package com.tony.erp.service.material;

import com.github.pagehelper.PageHelper;
import com.tony.erp.dao.MaterialMapper;
import com.tony.erp.domain.Material;
import com.tony.erp.utils.KeyGeneratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Transactional
    public Material checkSnExist(String msn){
        return materialMapper.checkBySn(msn);
    }

    @Transactional
    public List<Material> getAllMaterials(int pageNum){
        PageHelper.startPage(pageNum,10);
        return materialMapper.getAllMaterials();
    }

    @Transactional
    public int upMaterial(Material material){
        return materialMapper.updateByPrimaryKeySelective(material);
    }

    @Transactional
    public int addMaterial(Material material){
        material.setmId(KeyGeneratorUtils.keyUUID());
        material.setmStatus("1");
        return materialMapper.insert(material);
    }
}
