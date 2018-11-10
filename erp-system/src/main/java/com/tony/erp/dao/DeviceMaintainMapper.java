package com.tony.erp.dao;

import com.tony.erp.domain.DeviceMaintain;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceMaintainMapper {
    int deleteByPrimaryKey(String hisId);

    int insert(DeviceMaintain record);

    int insertSelective(DeviceMaintain record);

    DeviceMaintain selectByPrimaryKey(String hisId);

    int updateByPrimaryKeySelective(DeviceMaintain record);

    int updateByPrimaryKey(DeviceMaintain record);

    List<DeviceMaintain> selectByDeviceCode(String device_code);

    List<DeviceMaintain> selectAll();
}