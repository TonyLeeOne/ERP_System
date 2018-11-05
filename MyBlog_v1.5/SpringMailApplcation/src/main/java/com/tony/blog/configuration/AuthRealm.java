package com.tony.blog.configuration;

import com.tony.blog.entity.Module;
import com.tony.blog.entity.Role;
import com.tony.blog.entity.User;
import com.tony.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 类AuthRealm完成根据用户名去数据库的查询,并且将用户信息放入shiro中
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 获取用户权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User u = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissions = new ArrayList<>();
        Set<Role> role = u.getRole();
        if (!role.isEmpty() && role.size() > 0) {
            role.forEach(r -> {
                Set<Module> modules = r.getModule();
                modules.forEach(module -> {
                    permissions.add(module.getMname());
                });
            });
        }
        //将权限放入shiro中.
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 根据前台输入的用户名获取数据库中的用户信息，并保存到SimpleAuthenticationInfo中
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) authenticationToken;
//        获取前台输入的用户密码
        String username = passwordToken.getUsername();

        User u = userService.findUserProperties(username);
        if (ObjectUtils.isEmpty(u)) {
            log.error("当前用户没找到:[{}]" + username);
            throw new UnknownAccountException();
        }
//        使用用户名加盐
        ByteSource source = ByteSource.Util.bytes(u.getUsername());
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(u, u.getPassword(), source, this.getClass().getName());
    }
}
