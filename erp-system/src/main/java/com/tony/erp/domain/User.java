package com.tony.erp.domain;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author jli2
 * @date 2018/11/12
 */
@Data
public class User {

    private String id;

    private String uname;

    private String upass;

    private String departId;

    private String status;

    private Profile profile=new Profile();
    
    private Department department = new Department();

    private Set<Role> roles = new HashSet<>();

}