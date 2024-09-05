package com.java.tutorial.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author meta
 */
@Configuration
@RequiredArgsConstructor
public class InterceptorAdvice implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 将拦截器注入到容器中
        final InterceptorRegistration registration =
            registry.addInterceptor(new LogInterceptor()).order(Integer.MIN_VALUE);
        registration.addPathPatterns("/**");
    }
}
