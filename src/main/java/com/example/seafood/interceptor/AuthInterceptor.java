package com.example.seafood.interceptor;

import com.example.seafood.pojo.User;
import com.example.seafood.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("manage")) {
            String userId = request.getParameter("userId");
            User userById = userService.getUserById(Integer.valueOf(userId));
            return userById.getRole() == 0;
        }
        return true;
    }
}
