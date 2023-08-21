package com.java.tutorial.project.common.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * Redis属性配置
 *
 */
@ConfigurationProperties(prefix = "spring.data.redis")
public final class RedisProperties {

    private String host;
    private int port;
    private int database;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
