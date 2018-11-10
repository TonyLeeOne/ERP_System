package com.tony.erp.dao;

import com.tony.erp.domain.MaterialPurchase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialPurchaseMapper {
    int deleteByPrimaryKey(String mphId);

    int insert(MaterialPurchase record);

    int insertSelective(MaterialPurchase record);

    MaterialPurchase selectByPrimaryKey(String mphId);

    int updateByPrimaryKeySelective(MaterialPurchase record);

    int updateByPrimaryKey(MaterialPurchase record);

    List<MaterialPurchase> selectAll();

    List<MaterialPurchase> selectByMphSn(String mphSn);
}