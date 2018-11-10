package com.tony.erp.dao;

import com.tony.erp.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    int deleteByPrimaryKey(String proId);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String proId);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> getAllProducts();
}