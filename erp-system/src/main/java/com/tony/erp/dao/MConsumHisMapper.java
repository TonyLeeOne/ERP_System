package com.tony.erp.dao;

import com.tony.erp.domain.MConsumHis;

public interface MConsumHisMapper {
    int deleteByPrimaryKey(String mchId);

    int insert(MConsumHis record);

    int insertSelective(MConsumHis record);

    MConsumHis selectByPrimaryKey(String mchId);

    int updateByPrimaryKeySelective(MConsumHis record);

    int updateByPrimaryKeyWithBLOBs(MConsumHis record);

    int updateByPrimaryKey(MConsumHis record);
}