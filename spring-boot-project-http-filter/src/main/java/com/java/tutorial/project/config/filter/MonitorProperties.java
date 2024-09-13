package com.java.tutorial.project.config.filter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author meta
 */
@Data
@Component
@ConfigurationProperties(prefix = "monitor")
public class MonitorProperties {

    private boolean enabled;
    /**
     * 跳过的接口地址
     */
    private String filterWhiteUrl;
}
