package com.java.tutorial.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author meta
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setIncludeHeaders(true);
        filter.setIncludeClientInfo(true);
        filter.setAfterMessagePrefix("REQUEST DATA-");
        return filter;
    }
}
