package com.tony.erp.dao;

import com.tony.erp.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(String oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    <T, K, V> List<T> find(Map<K, V> params);

}