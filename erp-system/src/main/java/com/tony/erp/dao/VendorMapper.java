package com.tony.erp.dao;

import com.tony.erp.domain.Vendor;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Repository
public interface VendorMapper {
    int deleteByPrimaryKey(String vId);

    int insert(Vendor record);

    int insertSelective(Vendor record);

    Vendor selectByPrimaryKey(String vId);

    int updateByPrimaryKeySelective(Vendor record);

    int updateByPrimaryKey(Vendor record);

    List<Vendor> getAllVendors();
}