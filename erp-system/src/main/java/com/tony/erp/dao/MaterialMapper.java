package com.tony.erp.dao;

import com.tony.erp.domain.Material;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialMapper {
    int deleteByPrimaryKey(String mId);

    int insert(Material record);

    int insertSelective(Material record);

    Material checkBySn(String m_sn);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> getAllMaterials();
}