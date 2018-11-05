package com.tony.blog.configuration;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class CredentialsMatcher extends SimpleCredentialsMatcher {

    private static final String hasAlgorithmName = "MD5";
    private static final int hasIterations = 1024;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        //获取前台传过来的账号和密码
        String pwd = String.valueOf(passwordToken.getPassword());
        String username = passwordToken.getUsername();
        //使用用户名作为盐值
        ByteSource byteSource = ByteSource.Util.bytes(username);
        //返回加盐结果
        Object result = new SimpleHash(hasAlgorithmName, pwd, byteSource, hasIterations);
        //获取info中数据库中的密码
        String credential = (String) info.getCredentials();
        return this.equals(result.toString(), credential);
    }
}
