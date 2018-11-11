package com.tony.erp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class ManPlan {
    private String mpId;

    private String mpSn;

    private String mpProCode;

    private String mpStartDate;

    private String mpEndDate;

    private Integer mpCount;

    private String mpOrderId;

    private String mpStatus;

    private Set<ManOrder> manOrders = new HashSet<>();
}