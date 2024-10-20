package com.java.tutorial.project.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 使用注入bean的方式调用Caffeine
 *
 * @author meta
 */
@Configuration
public class CacheConfig {

    /**
     * @return
     */
    @Bean
    public Cache<String, Object> caffeineCache() {
        return Caffeine.newBuilder()
            // 设置最后一次写入或访问后两个小时后过期
            .expireAfterWrite(7200, TimeUnit.SECONDS)
            // 初始的缓存空间大小
            .initialCapacity(100)
            // 缓存的最大条数
            .maximumSize(1000).build();
    }
}