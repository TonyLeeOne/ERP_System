package com.tony.erp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode
public class Role {

    private String rid;

    private String rname;

    private Set<Module> modules=new HashSet<>();
}