package com.tony.blog.utils;

import com.tony.blog.utils.model.Result;

/**
 * 图片上传结果处理
 */
public class ResultUtils {

    public static Result success(String[] filePaths){
        Result result=new Result();
        result.setErrno(0);
        result.setData(filePaths);
        return result;
    }
    public static Result fail(){
       return success(null);
    }
}
