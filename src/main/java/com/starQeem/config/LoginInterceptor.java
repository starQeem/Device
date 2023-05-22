package com.starQeem.config;

import com.starQeem.pojo.user;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        user user = (com.starQeem.pojo.user)session.getAttribute("user");
        if(user == null) {
            // 如果用户未登录，则跳转到登录页面
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
