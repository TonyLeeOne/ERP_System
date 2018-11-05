package com.tony.erp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class Role  implements Serializable {
    private String rid;

    private String rname;

    private Set<Module> modules=new HashSet<>();
}