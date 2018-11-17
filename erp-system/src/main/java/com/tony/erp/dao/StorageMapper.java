package com.tony.erp.dao;

import com.tony.erp.domain.Storage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StorageMapper {

    int deleteByPrimaryKey(String stoId);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(String stoId);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    <T, K, V> List<T> find(Map<K, V> params);

}