package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class User {
    private String id;

    private String uname;

    private String upass;

    private String departId;

    private String status;

    private Department department=new Department();

    private Set<Role> roles=new HashSet<>();


    }