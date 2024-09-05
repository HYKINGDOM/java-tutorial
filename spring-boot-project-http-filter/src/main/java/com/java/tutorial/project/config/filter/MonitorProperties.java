package com.java.tutorial.project.config.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author kscs
 */
@Component
@Getter
@Setter
public class MonitorProperties {

    @Value("${monitor.enabled:true}")
    private boolean enabled;
    /**
     * 跳过的接口地址
     */
    @Value("${monitor.filter.whiteUrl:}")
    private String whiteUrl;
}
