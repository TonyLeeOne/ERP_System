package com.tony.erp.configuration.shiro;

import com.tony.erp.constant.Constant;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义shiro密码比较器
 */
public class CredentialMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token;
        //获取前台传过来的账号和密码
        String pwd = String.valueOf(passwordToken.getPassword());
        String username = passwordToken.getUsername();
        //获得用户输入的密码:(对usernmae加盐，加密密码)
        ByteSource source = ByteSource.Util.bytes(username);
        //返回加盐后的结果
        Object result = new SimpleHash(Constant.HasAlgorithmName, pwd, source, Constant.HasIterations);
        //获取info中获取user在数据库中的加密密码
        String credential = (String) info.getCredentials();
        return this.equals(result.toString(), credential);
    }
}
