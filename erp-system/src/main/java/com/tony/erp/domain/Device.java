package com.tony.erp.domain;

import lombok.Data;

@Data
public class Device {
    private String deviceId;

    private String deviceName;

    private String deviceStatus;

    private String devicePurDate;

    private String deviceCode;

    private String deviceVendor;

    private String deviceVendorTel;

    private String deviceUsedPeriod;

    private String deviceNote;

    private String devicePrice;

}