package com.java.tutorial.project.config.filter.config;


import com.java.tutorial.project.config.filter.HttpMonitorFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class MonitorConfiguration {
    private static final Log log = LogFactory.getLog(MonitorConfiguration.class);


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
        if (log.isInfoEnabled()) {
            log.info("httpMonitorFilter register successful");
        }
        return registration;
    }
}