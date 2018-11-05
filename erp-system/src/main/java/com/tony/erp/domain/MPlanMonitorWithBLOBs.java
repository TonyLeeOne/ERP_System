package com.tony.erp.domain;

import lombok.Data;

@Data
public class MPlanMonitorWithBLOBs extends MPlanMonitor {

    private String mpmCount;

    private String mpmWaitCount;

}