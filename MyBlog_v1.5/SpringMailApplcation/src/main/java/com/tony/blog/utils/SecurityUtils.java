package com.tony.blog.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class SecurityUtils {

    private static final String hasAlgorithmName = "MD5";
    private static final int hasIterations = 1024;

    /**
     * 以username作为盐值给密码加密
     * @param username
     * @param password
     * @return
     */
    public static String getSecurityResult(String username,String password){
        ByteSource byteSource = ByteSource.Util.bytes(username);
        Object result = new SimpleHash(hasAlgorithmName, password, byteSource, hasIterations);
        return result.toString().trim();

    }
}
