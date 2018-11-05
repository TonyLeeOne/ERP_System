package com.tony.blog.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Filter用来打印每一次的请求信息到log中
 */
@Slf4j
//@Component
//@WebFilter(urlPatterns = "/**", filterName = "LogFilter")
public class LogFilter implements Filter {

    private StringBuffer stringBuffer = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader("location","url");

        long time = System.currentTimeMillis();
        filterChain.doFilter(request, response);
        long responseTime = System.currentTimeMillis() - time;
        if (request.getRequestURL().indexOf("/favicon.ico") == -1) {
            stringBuffer = new StringBuffer();
            stringBuffer.append("\n--------------RequestInformation------------------");
            this.getHeadersInfo(request);
            stringBuffer.append("\n").append("Method:" + request.getMethod());
            stringBuffer.append("\n").append("URL:" + request.getRequestURL());
            stringBuffer.append("\n").append("Cookies:" + request.getCookies());
            stringBuffer.append("\n").append("RemoteAddress:" + request.getRemoteAddr());
            stringBuffer.append("\n").append("responseTime:" + responseTime + "mms");
            stringBuffer.append("\n--------------RequestInformation------------------");
            log.info(stringBuffer.toString());
        }

    }

    @Override
    public void destroy() {

    }

    /**
     * 对Head内容进行解析
     *并将解析的内容添加到StringBuffer中
     * @param request
     */
    private void getHeadersInfo(HttpServletRequest request) {
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            stringBuffer.append("\n").append(key + ":" + value);
        }
    }


}
