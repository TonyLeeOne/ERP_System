package com.tony.erp.domain;

import lombok.Data;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class MaterialConsume {
    private String mcId;

    private String mcMpSn;

    private String mcMoSn;

    private String mcMSn;

    private Integer mcCountNeeded;

    private Integer mcCountIndeed;

    private String mcRequestor;

    private String mcOperator;

    private String mcDate;

    private String mcStatus;

    private ManOrder manOrder=new ManOrder();

    private ManPlan manPlan=new ManPlan();

    private Product product=new Product();

    private Material material=new Material();



}