package com.tony.erp.domain;

import lombok.Data;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class Device {
    private String deviceId;

    private String deviceName;

    private String devicePurDate;

    private Integer devicePrice;

    private String deviceCode;

    private String deviceVendor;

    private String deviceVendorTel;

    private String deviceUsedPeriod;

    private String deviceNote;

    private String deviceStatus;

    private Vendor vendor = new Vendor();

}