package com.tony.blog.entity;

import lombok.Data;

@Data
public class Comment {

    private String id;

    private String bid;

    private String content;

    private String tid;

    private String uid;

    private String ctime;

    private String status;

    private String ouid;

    private User user=new User();

    private Profile profile=new Profile();

}