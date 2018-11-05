package com.tony.erp.dao;

import com.tony.erp.domain.Material;

public interface MaterialMapper {
    int deleteByPrimaryKey(String mId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String mId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKeyWithBLOBs(Material record);

    int updateByPrimaryKey(Material record);
}