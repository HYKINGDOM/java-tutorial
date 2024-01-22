package com.java.tutorial.project.config;


/**
 * @author meta
 */
public interface HeaderKey {

    /**
     * 来源 根据端来划分
     */
    String SOURCE = "SOURCE";

    /**
     * 渠道来源，根据载体划分
     */
    String CHANNEL_SOURCE = "CHANNEL-SOURCE";

    /**
     * 请求客户端的ip
     */
    String X_FORWARDED_FOR = "R-IP";
}
