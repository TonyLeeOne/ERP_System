package com.tony.erp.exception;

import com.tony.erp.constant.Constant;
import com.tony.erp.exception.model.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult defaultExceptionHandler(HttpServletRequest request, Exception e){

        if(e instanceof java.lang.NullPointerException){
            return ResponseResult.serverError(Constant.ARG_EXCEPTION,null);
        }
        return ResponseResult.serverError(e.getCause().getMessage(),null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResponseResult defaultHandler(HttpServletRequest request, Exception e){

        if(e instanceof java.lang.NullPointerException){
            return ResponseResult.serverError(Constant.ARG_EXCEPTION,null);
        }
        return ResponseResult.serverError(e.getCause().getMessage(),null);
    }
}
