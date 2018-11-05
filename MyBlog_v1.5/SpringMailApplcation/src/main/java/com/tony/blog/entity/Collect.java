package com.tony.blog.entity;

import lombok.Data;

@Data
public class Collect {

    private String id;

    private String uid;

    private String bid;

    private Blog blog=new Blog();



}