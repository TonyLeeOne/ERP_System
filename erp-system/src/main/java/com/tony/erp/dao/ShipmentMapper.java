package com.tony.erp.dao;

import com.tony.erp.domain.Shipment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ShipmentMapper {
    
    int deleteByPrimaryKey(String sId);

    int insert(Shipment record);

    int insertSelective(Shipment record);

    Shipment selectByPrimaryKey(String sId);

    int updateByPrimaryKeySelective(Shipment record);

    int updateByPrimaryKey(Shipment record);

    <T, K, V> List<T> find(Map<K, V> params);

}