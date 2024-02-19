package com.java.tutorial.tool.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author HY
 */

@Data
@Component
@ConfigurationProperties(prefix = "demo")
public class TestDemoProperties {

    private boolean configs;

    private String AAA;

}
