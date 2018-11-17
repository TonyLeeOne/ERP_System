package com.tony.erp.domain;

import lombok.Data;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class Shipment {
    private String sId;

    private String sOrderNo;

    private String sProCode;

    private Integer sShipCount;

    private String sAuditor;

    private String sSurer;

    private String sAuditDate;

    private String sShipDate;

    private String sStatus;

    private Order order=new Order();

    private Product product=new Product();


}