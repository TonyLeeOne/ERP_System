package com.tony.erp.utils;

import com.tony.erp.domain.User;
import org.apache.shiro.SecurityUtils;

public class CurrentUser {

    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
