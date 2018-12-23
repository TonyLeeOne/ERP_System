package com.tony.erp.domain;

import lombok.Data;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class Product {

    private String proId;

    private String proCode;

    private String proName;

    private Integer proCount;

    private Float proPrice;

    private String proImage;

    private String proNote;

    private String proStatus;

    private String proUnit;

    private String proLocked;

}