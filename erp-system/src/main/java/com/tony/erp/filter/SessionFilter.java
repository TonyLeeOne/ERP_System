package com.tony.erp.filter;

import org.apache.shiro.SecurityUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author jli2
 * @date  2018/11/12
 * session过滤器，修改response对象，设置session超时标识
 */
//@Component
//@WebFilter(urlPatterns = "/*", filterName = "sessionFilter")
public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (!SecurityUtils.getSubject().isAuthenticated()) {
            //判断session里是否有用户信息,且是否为ajax请求，如果是ajax请求响应头会有，x-requested-with
            //在响应头设置session状态
            if (req.getHeader("x-requested-with") != null
                    && "XMLHttpRequest".equalsIgnoreCase(req.getHeader("x-requested-with"))) {
                res.setHeader("session-status", "timeout");
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
