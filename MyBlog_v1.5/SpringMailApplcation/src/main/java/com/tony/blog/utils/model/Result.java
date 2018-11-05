package com.tony.blog.utils.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 图片上传结构返回model
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    private static final Long serialVersionUID=0L;

    private Integer errno;
    private String[] data;
}
