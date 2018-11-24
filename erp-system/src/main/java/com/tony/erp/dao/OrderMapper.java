package com.tony.erp.dao;

import com.tony.erp.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface OrderMapper {

    int deleteByPrimaryKey(String oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String oId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    <T, K, V> List<T> find(Map<K, V> params);

    int getTotal();

    int batchDeleteByOno(@Param("orderNos")String[] orderNos);

    List<String> getONos();

}