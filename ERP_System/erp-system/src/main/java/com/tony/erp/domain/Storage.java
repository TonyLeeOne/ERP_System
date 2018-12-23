package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jli2
 * @date  2018/11/12
 */
@Data
public class Storage {
    private String stoId;

    private String stoMpSn;

    private String stoMoSn;

    private String stoProCode;

    private Integer stoIndeedNum;

    private String stoRealDate;

    private String stoSurer;

    private String stoSender;

    private String stoStatus;

    private Product product=new Product();

    private ManOrder manOrder=new ManOrder();



}