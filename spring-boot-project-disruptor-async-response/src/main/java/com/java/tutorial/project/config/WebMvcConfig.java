package com.java.tutorial.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
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

    @Resource
    private ThreadPoolTaskExecutor mvcAsyncTaskExecutor;

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //异步操作的超时时间，值为0或者更小，表示永不超时
        configurer.setDefaultTimeout(60001);
        configurer.setTaskExecutor(mvcAsyncTaskExecutor);
    }
}
