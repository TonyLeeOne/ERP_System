package com.tony.erp.dao;

import com.tony.erp.domain.MPlan;

public interface MPlanMapper {
    int deleteByPrimaryKey(String mId);

    int insert(MPlan record);

    int insertSelective(MPlan record);

    MPlan selectByPrimaryKey(String mId);

    int updateByPrimaryKeySelective(MPlan record);

    int updateByPrimaryKeyWithBLOBs(MPlan record);

    int updateByPrimaryKey(MPlan record);
}