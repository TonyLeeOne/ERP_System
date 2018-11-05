package com.tony.blog.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SQLException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Integer code;

    public SQLException(){}

    public SQLException(ExceptionDef exceptionDef){
        super(exceptionDef.getMsg());
        this.code=exceptionDef.getCode();
    }


}
