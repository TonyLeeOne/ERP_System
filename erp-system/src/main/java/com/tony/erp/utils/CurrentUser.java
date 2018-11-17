package com.tony.erp.utils;

import com.tony.erp.domain.User;
import org.apache.shiro.SecurityUtils;
/**
 * @author jli2
 * @date  2018/11/12
 */
public class CurrentUser {

    public static User getCurrentUser(){
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
