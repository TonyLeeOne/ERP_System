package com.tony.erp.dao;

import com.tony.erp.domain.MPurHis;

public interface MPurHisMapper {
    int deleteByPrimaryKey(String mphId);

    int insert(MPurHis record);

    int insertSelective(MPurHis record);

    MPurHis selectByPrimaryKey(String mphId);

    int updateByPrimaryKeySelective(MPurHis record);

    int updateByPrimaryKeyWithBLOBs(MPurHis record);

    int updateByPrimaryKey(MPurHis record);
}