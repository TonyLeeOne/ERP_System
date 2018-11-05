package com.tony.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class User implements Serializable {
    
    private String uid;

    private String username;

    private String password;

    private Integer level;

    private Set<Role> role=new HashSet<>();

}