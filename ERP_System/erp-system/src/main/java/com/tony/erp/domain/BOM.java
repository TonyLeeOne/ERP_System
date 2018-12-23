package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BOM {
    private String bId;

    private String bPcode;

    private String bCode;

    private String bName;

    private Product product=new Product();

    private Set<BomDetail> details=new HashSet<>();
}