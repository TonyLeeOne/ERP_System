package com.tony.blog.filters;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component("roles")
@Slf4j
public class RolesCustomFiler extends AuthorizationFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        org.apache.shiro.subject.Subject subject=getSubject(servletRequest,servletResponse);
        String [] array= (String[]) o;
        log.info(array.toString());
        if(array==null||array.length==0){
            return false;
        }
        for(int i=0;i<array.length;i++){
            if(subject.hasRole(array[i])){
                return true;
            }
        }
        return false;
    }
}
