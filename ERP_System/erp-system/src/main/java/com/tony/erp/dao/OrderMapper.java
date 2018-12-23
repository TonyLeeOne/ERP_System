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

    /**
     * 根据主键删除记录
     * @param oId
     * @return
     */
    int deleteByPrimaryKey(String oId);

    /**
     * 全量插入新的订单
     * @param record
     * @return
     */
    int insert(Order record);

    /**
     * 可选插入
     * @param record
     * @return
     */
    int insertSelective(Order record);

    /**
     * 根据主键查找订单信息
     * @param oId
     * @return
     */
    Order selectByPrimaryKey(String oId);

    /**
     * 根据主键更新订单信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * 根据主键全量更新订单信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Order record);

    /**
     * 多条件查询订单信息
     * @param params
     * @param <T>
     * @param <K>
     * @param <V>
     * @return
     */
    <T, K, V> List<T> find(Map<K, V> params);

    /**
     * 获取未完成的订单数量
     * @return
     */
    int getTotal();

    /**
     * 批量删除订单信息
     * @param orderNos
     * @return
     */
    int batchDeleteByOno(@Param("orderNos")String[] orderNos);

    /**
     * 根据订单号查询订单信息
     * @param oStatus
     * @return
     */
    List<String> getONos(@Param("oStatus") String oStatus);

    /**
     * dashboard信息查询接口
     * @return
     */
    List<String> dataCollection();

    /**
     * 条件查询
     * @param record
     * @return
     */
    List<Order> search(Order record);
}