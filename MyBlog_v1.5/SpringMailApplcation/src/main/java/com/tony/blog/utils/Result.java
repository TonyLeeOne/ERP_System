package com.tony.blog.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final Long serialVersionUID=0L;

    private Integer errno;
    private String[] data;
}
