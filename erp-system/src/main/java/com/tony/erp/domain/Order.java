package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class Order {

    /**
     * 主键
     */
    private String oId;

    /**
     * 修改者
     */
    private String oModifier;

    /**
     * 创建者
     */
    private String oCreator;

    /**
     * 订单编号，唯一
     */
    private String oNo;

    /**
     * 公司订单编号
     */
    private String oComNo;

    /**
     * 产品标号
     */
    private String oProductCode;

    /**
     * 订单数量
     */
    private Integer oCount;

    /**
     * 订单实际数量
     */
    private Integer oIndeedCount;

    /**
     * 订单创建日期
     */
    private String oCreateDate;

    /**
     * 出货日期
     */
    private String oShipmentDate;

    /**
     * 客户名
     */
    private String oCustomName;

    /**
     * 单价
     */
    private String oPay;

    /**
     * 支付币种
     */
    private String oPayCategory;

    /**
     * 汇率
     */
    private String oExchangeRate;

    /**
     * 出货方式
     */
    private String oShipmentMethod;

    /**
     * 客户联系人
     */
    private String oContacts;

    /**
     * 客户联系方式
     */
    private String oTel;

    /**
     * 客户地址
     */
    private String oAddress;

    /**
     * 业务员姓名
     */
    private String oSalesman;

    /**
     * 业务员部门
     */
    private String oSalesmanDepart;

    /**
     * 业务员联系方式
     */
    private String oSalesmanContact;

    /**
     * 审核人
     */
    private String oAuditor;

    /**
     * 审核日期
     */
    private String oAuditDate;

    /**
     * 备注
     */
    private String oNote;

    /**
     * 订单当前状态
     */
    private String oStatus;

    private Product product=new Product();

    private Set<ManPlan> plans=new HashSet<>();

}