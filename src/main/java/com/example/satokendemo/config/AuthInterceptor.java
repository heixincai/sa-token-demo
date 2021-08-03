package com.example.satokendemo.config;

import com.example.satokendemo.common.exception.MyException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token  = request.getHeader("X-Token");
        if(token == null || token.isEmpty()){
            throw new MyException("Token为空，请先登录！");
        }
        return true;
    }
}
