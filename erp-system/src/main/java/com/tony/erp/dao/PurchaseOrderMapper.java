package com.tony.erp.dao;

import com.tony.erp.domain.MaterialPurchase;
import com.tony.erp.domain.PurchaseOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderMapper {

    int deleteByPrimaryKey(String poId);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    PurchaseOrder selectByPrimaryKey(String poId);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

    List<PurchaseOrder> selectAll(MaterialPurchase param);

    List<PurchaseOrder> selectByOno(@Param("poOno") String poOno);
}