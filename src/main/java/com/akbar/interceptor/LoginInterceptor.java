package com.akbar.interceptor;

import com.akbar.util.JwtUtil;
import com.akbar.util.Result;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            if (token == null) {
                throw new RuntimeException("没有token");
            }
            JwtUtil.parseToken(token);
            return true;
        } catch (Exception e) {
            Result<Void> error = Result.error( "NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            response.setStatus(401);
            response.getWriter().write(notLogin);
            return false;
        }
    }
}
