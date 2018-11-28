package com.tony.erp.domain.image;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jli2
 * @date 11/16/2018 10:58 AM
 **/

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID=1L;
    private int status;
    private String[] data;


}
