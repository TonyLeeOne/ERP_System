package com.tony.erp.utils;

import com.tony.erp.constant.Constant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SecurityUtils {
    public static String getSecurityResult(String username,String password){
        ByteSource byteSource=ByteSource.Util.bytes(username);
        return new SimpleHash(Constant.ALGORITHM,password,byteSource,Constant.ITERATORS).toString().trim();
    }
}
