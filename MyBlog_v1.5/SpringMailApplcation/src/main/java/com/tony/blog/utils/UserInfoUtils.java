package com.tony.blog.utils;

import com.tony.blog.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class UserInfoUtils {

    public static User getUser(){
        Subject subject= SecurityUtils.getSubject();
        return (User) subject.getPrincipal();
    }
}
