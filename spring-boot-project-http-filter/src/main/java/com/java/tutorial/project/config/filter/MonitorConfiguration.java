package com.java.tutorial.project.config.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author kscs
 */
@Configuration
public class MonitorConfiguration {

    @Resource
    private MonitorProperties monitorProperties;

    /**
     * 注入过滤器管理
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean<HttpMonitorFilter> httpMonitorFilterRegistration() {
        FilterRegistrationBean<HttpMonitorFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpMonitorFilter(monitorProperties));
        registration.addUrlPatterns("/*");
        registration.setName("httpMonitorFilter");
        registration.setOrder(Integer.MAX_VALUE - 1);
        return registration;
    }
}