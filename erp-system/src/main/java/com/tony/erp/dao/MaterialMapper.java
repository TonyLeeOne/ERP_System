package com.tony.erp.dao;

import com.tony.erp.domain.Material;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialMapper {
    int deleteByPrimaryKey(String mId);

    int insert(Material record);

    int insertSelective(Material record);

    Material selectByPrimaryKey(String mId);

    int updateByPrimaryKeySelective(Material record);

    int updateByPrimaryKey(Material record);
}