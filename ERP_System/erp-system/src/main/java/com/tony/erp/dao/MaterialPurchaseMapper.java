package com.tony.erp.dao;

import com.tony.erp.domain.MaterialPurchase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface MaterialPurchaseMapper {
    /**
     * 根据主键删除采购记录
     * @param mphId
     * @return
     */
    int deleteByPrimaryKey(String mphId);

    /**
     * 插入新的采购记录
     * @param record
     * @return
     */
    int insert(MaterialPurchase record);

    /**
     * 有选择的插入新的采购记录
     * @param record
     * @return
     */
    int insertSelective(MaterialPurchase record);

    /**
     * 根据主键查找采购记录
     * @param mphId
     * @return
     */
    MaterialPurchase selectByPrimaryKey(String mphId);

    /**
     * 根据主键有选择的更新采购记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MaterialPurchase record);

    /**
     * 根据主键更新采购记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(MaterialPurchase record);

    /**
     * 查找所有采购记录
     * @return
     */
    List<MaterialPurchase> selectAll();

    /**
     * 根据物料编号查找所有采购记录
     * @param mphSn
     * @return
     */
    List<MaterialPurchase> selectByMphSn(String mphSn);

    /**
     * 查找采购订单下所有采购清单的状态
     * @param mphPoId
     * @return
     */
    List<String> selectStatusByMpoId(@Param("mphPoId") String mphPoId);
}