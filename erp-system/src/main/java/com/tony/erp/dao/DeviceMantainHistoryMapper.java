package com.tony.erp.dao;

import com.tony.erp.domain.DeviceMantainHistory;

public interface DeviceMantainHistoryMapper {
    int deleteByPrimaryKey(String hisId);

    int insert(DeviceMantainHistory record);

    int insertSelective(DeviceMantainHistory record);

    DeviceMantainHistory selectByPrimaryKey(String hisId);

    int updateByPrimaryKeySelective(DeviceMantainHistory record);

    int updateByPrimaryKey(DeviceMantainHistory record);
}