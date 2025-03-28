package com.akbar.config;

import com.akbar.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(
                        "/admin/**",
                        "/tag/**",
                        "/category/**",
                        "/article/**",
                        "/image/**",
                        "/todo/**"
                )
                .excludePathPatterns(
                        "/admin/login", "/admin/info",
                        "/tag/list",
                        "/category/list",
                        "/article/list",
                        "/todo/list"
                );
    }
}
