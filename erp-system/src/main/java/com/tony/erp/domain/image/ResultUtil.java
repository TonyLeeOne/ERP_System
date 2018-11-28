package com.tony.erp.domain.image;

/**
 * @author jli2
 * @date 11/16/2018 11:10 AM
 **/
public class ResultUtil {

    public static Result success(String[] filePaths){
        Result result=new Result();
        result.setStatus(0);
        result.setData(filePaths);
        return result;
    }
    public static Result fail(){
        Result result=new Result();
        result.setStatus(-1);
        result.setData(null);
        return result;
    }

}
