package com.tony.erp.dao;

import com.tony.erp.domain.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(String proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String proId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKeyWithBLOBs(Product record);

    int updateByPrimaryKey(Product record);
}