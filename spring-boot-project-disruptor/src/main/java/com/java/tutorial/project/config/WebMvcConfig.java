package com.java.tutorial.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private MdcInterceptor mdcInterceptor;

    /**
     * 将拦截器注入到容器中
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final InterceptorRegistration registration = registry.addInterceptor(mdcInterceptor).order(Integer.MIN_VALUE);
        registration.addPathPatterns("/**");
    }
}
