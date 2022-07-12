package com.hmdp.config;

import com.hmdp.utils.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Project Name:hm-dianping
 * File Name:null.java
 * Package Name:com.hmdp.config
 * Date:2022/7/12 16:29
 * Copyright (c) 2022, szxxwang@outlook.com All Rights Reserved.
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(
                "/shot-type/**",
                "/shop/**",
                "/upload/**",
                "/blog/hot",
                "/user/code",
                "/user/login"
        );

    }
}
