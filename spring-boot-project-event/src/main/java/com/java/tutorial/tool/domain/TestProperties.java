package com.java.tutorial.tool.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HY
 */
@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {

    @Setter
    @Getter
    private List<Config> configs;

    /**
     * 根据name获取配置
     */
    public Config getByName(String name) {
        for (Config c : configs) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    @Data
    private static class Config {
        private String name;
        private Integer value;
        private String msg;
    }
}