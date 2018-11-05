package com.tony.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
public class Role implements Serializable {
    private String rid;
    private String rname;
    private Set<Module> module=new HashSet<>();
    private Set<User> users=new HashSet<>();
}
