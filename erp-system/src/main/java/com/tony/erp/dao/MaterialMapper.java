package com.tony.erp.dao;

import com.tony.erp.domain.Material;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface MaterialMapper {

    int deleteByPrimaryKey(String mId);

    int insert(Material record);

    int insertSelective(Material record);

    Material checkBySn(String msn);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);

    List<Material> getAllMaterials();

    List<String> getAvailableMaterials();

}