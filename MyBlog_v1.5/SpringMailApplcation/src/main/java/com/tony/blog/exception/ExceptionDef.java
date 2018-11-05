package com.tony.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExceptionDef {
    UNKNOWN_ERROR(-1,"未知异常"),
    SUCCESS(0,"请求成功"),
    ERROR(1,"请求失败");

    private Integer code;
    private String msg;

}
