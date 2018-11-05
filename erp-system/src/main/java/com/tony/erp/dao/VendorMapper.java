package com.tony.erp.dao;

import com.tony.erp.domain.Vendor;

public interface VendorMapper {
    int deleteByPrimaryKey(String vId);

    int insert(Vendor record);

    int insertSelective(Vendor record);

    Vendor selectByPrimaryKey(String vId);

    int updateByPrimaryKeySelective(Vendor record);

    int updateByPrimaryKey(Vendor record);
}