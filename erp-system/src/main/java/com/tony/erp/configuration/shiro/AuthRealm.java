package com.tony.erp.configuration.shiro;

import com.tony.erp.constant.Constant;
import com.tony.erp.domain.Module;
import com.tony.erp.domain.Role;
import com.tony.erp.domain.User;
import com.tony.erp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.security.auth.login.AccountLockedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/**
 * @author jli2
 * @date  2018/11/12
 * 自定义authRealm
 */
@Slf4j
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user;
        user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> perms = new ArrayList<>();
        List<String> rooles = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            roles.forEach(role -> {
                rooles.add(role.getRname());
                Set<Module> modules = role.getModules();
                if (!CollectionUtils.isEmpty(modules)) {
                    modules.forEach(m -> {
                        perms.add(m.getMname());
                    });
                }

            });
        }

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(perms);
        authorizationInfo.addRoles(rooles);
        return authorizationInfo;
    }

    /**
     * 认证
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
        User u = userService.getUserPropertiesByUsername(username);
        if (ObjectUtils.isEmpty(u)) {
            log.error("当前用户没找到:[{}]", username);
            throw new UnknownAccountException();
        }
//        使用用户名加盐
        ByteSource source = ByteSource.Util.bytes(u.getUname());
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(u, u.getUpass(), source, this.getClass().getName());
    }
}
