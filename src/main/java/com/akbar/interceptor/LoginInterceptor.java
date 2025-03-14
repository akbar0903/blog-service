package com.akbar.interceptor;

import com.akbar.constant.JwtClaimsConstant;
import com.akbar.context.BaseContext;
import com.akbar.properties.JwtProperties;
import com.akbar.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Log4j2
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验有没有带jwt
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader(jwtProperties.getTokenName());

        try {
            log.info("jwt令牌：{}", token);
            Claims claims = JwtUtil.parseJwt(jwtProperties.getSecretKey(), token);
            Integer adminId = (Integer) claims.get(JwtClaimsConstant.ADMIN_ID);
            String role = (String) claims.get(JwtClaimsConstant.ROLE);
            log.info("adminId:{}", adminId);
            log.info("role:{}", role);
            // 存储到ThreadLocal中
            BaseContext.setCurrentAdminId(adminId);
            BaseContext.setCurrentAdminRole(role);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }


    /**
     * 拦截结束以后删除ThreadLocal中的数据
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  the handler (or {@link HandlerMethod}) that started asynchronous
     *                 execution, for type and/or instance examination
     * @param ex       any exception thrown on handler execution, if any; this does not
     *                 include exceptions that have been handled through an exception resolver
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        BaseContext.clear();
    }
}
