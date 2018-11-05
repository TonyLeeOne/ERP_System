package com.tony.erp.domain;

import lombok.Data;

@Data
public class DeviceMantainHistory {
    private String hisId;

    private String deviceCode;

    private String hisDate;

    private String hisOperator;

    private String hisResult;

    private String hisNote;

}