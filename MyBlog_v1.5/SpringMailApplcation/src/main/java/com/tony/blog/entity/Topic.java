package com.tony.blog.entity;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Topic {

    private String id;

    private String uid;

    private String subject;

    private String category;

    private String status;

    private String ctime;

    private String content;

    private Profile profile=new Profile();

    private User user=new User();

    private Set<Comment> comments=new HashSet<>();

}