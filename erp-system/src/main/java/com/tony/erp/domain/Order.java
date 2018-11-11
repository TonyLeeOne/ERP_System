package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Order {

    private String oId;

    private String oModifier;

    private String oCreator;

    private String oNo;

    private String oComNo;

    private String oProductCode;

    private Integer oCount;

    private Integer oIndeedCount;

    private String oCreateDate;

    private String oShipmentDate;

    private String oCustomName;

    private String oPay;

    private String oPayCategory;

    private String oExchangeRate;

    private String oShipmentMethod;

    private String oContacts;

    private String oTel;

    private String oAddress;

    private String oSalesman;

    private String oSalesmanDepart;

    private String oSalesmanContact;

    private String oAuditor;

    private String oAuditDate;

    private String oNote;

    private String oStatus;

    private Product product=new Product();

    private Set<ManPlan> plans=new HashSet<>();

}