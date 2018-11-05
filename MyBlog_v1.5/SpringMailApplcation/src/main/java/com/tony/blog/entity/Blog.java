package com.tony.blog.entity;

import lombok.Data;

@Data
public class Blog {

    private String id;

    private String uid;

    private String subject;

    private String ptime;

    private String utime;

    private String tag;

    private String contents;

    private User user = new User();
}