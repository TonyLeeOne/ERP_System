package com.tony.erp.interceptor;

import com.tony.erp.constant.Constant;
import com.tony.erp.utils.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jli2
 * @date 12/6/2018 3:41 PM
 **/
@Slf4j
public class SessionInteceptor implements HandlerInterceptor {

    private static String[] IGNORE_URI = {"/login", "/logout", "/error", "/doLogin"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag = false;
        String current = request.getRequestURL().toString().trim();
        for (String s : IGNORE_URI) {
            if (current.contains(s)) {
                flag = true;
                break;
            }
        }

        if (!flag) {
            if (ObjectUtils.isEmpty(CurrentUser.getCurrentUser())) {
                if (request.getHeader("x-requested-with") != null &&
                        request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                     //在响应头设置session状态
                    response.setHeader("session-status", "timeout");
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().print("error");
                } else {
                    response.sendRedirect(request.getContextPath() + "/login");
                }

                return flag;
            }
        }

        return true;
    }


}
