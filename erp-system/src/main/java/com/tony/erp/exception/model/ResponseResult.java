package com.tony.erp.exception.model;

import com.tony.erp.constant.Constant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ResponseResult {

    private int status;
    private String msg;
    private Object data;

    public static ResponseResult serverError(String msg,Object data){
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg,data);
    }

    public static ResponseResult argException(Object data){
        return new ResponseResult(HttpStatus.INTERNAL_SERVER_ERROR.value(),Constant.ARG_EXCEPTION,data);
    }


    public static ResponseResult reponseOK(Object data){
        return new ResponseResult(HttpStatus.OK.value(),Constant.RESPONSE_SUCCESS,data);
    }


}
