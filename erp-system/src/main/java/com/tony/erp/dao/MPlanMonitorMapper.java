package com.tony.erp.dao;

import com.tony.erp.domain.MPlanMonitor;
import com.tony.erp.domain.MPlanMonitorWithBLOBs;

public interface MPlanMonitorMapper {
    int deleteByPrimaryKey(String mpmId);

    int insert(MPlanMonitorWithBLOBs record);

    int insertSelective(MPlanMonitorWithBLOBs record);

    MPlanMonitorWithBLOBs selectByPrimaryKey(String mpmId);

    int updateByPrimaryKeySelective(MPlanMonitorWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MPlanMonitorWithBLOBs record);

    int updateByPrimaryKey(MPlanMonitor record);
}