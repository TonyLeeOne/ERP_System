package com.tony.blog.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultExceptionHandler(HttpServletRequest request, Exception e){
       return "EXCEPTION";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String defaultHandler(HttpServletRequest request, Exception e){
        return e.getCause().getMessage();
    }
}
