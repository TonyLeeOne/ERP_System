package com.tony.erp.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable {
    private String id;

    private String uname;

    private String upass;

    private String departid;

    private String status;

    private Set<Role> roles=new HashSet<>();



}