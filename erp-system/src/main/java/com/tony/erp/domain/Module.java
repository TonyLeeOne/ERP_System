package com.tony.erp.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Module implements Serializable {

    private String mid;

    private String mname;

    public String getMid() {
        return mid;
    }
}