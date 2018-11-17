package com.tony.erp.utils;

import com.tony.erp.constant.Constant;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class SecurityUtils {

    /**
     * MD5加密，uname作为盐值对upass加密
     * @param username
     * @param password
     * @return
     */
    public static String getSecurityResult(String username,String password){
        ByteSource byteSource=ByteSource.Util.bytes(username);
        return new SimpleHash(Constant.ALGORITHM,password,byteSource,Constant.ITERATORS).toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(getSecurityResult("test","test"));
    }
}
