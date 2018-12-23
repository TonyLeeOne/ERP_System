package com.tony.erp.domain;

import lombok.Data;

@Data
public class BomDetail {

    private String bdId;

    private String bdBcode;

    private String bdMsn;

    private Integer bdNum;

    private Float bdRate;

    private Material material=new Material();

}