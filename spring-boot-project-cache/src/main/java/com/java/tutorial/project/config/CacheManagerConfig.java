package com.java.tutorial.project.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 这个类使用Caffeine 替换掉spring boot的默认缓存 使用当前类的时候不用加CacheConfig 这个类的配置
 *
 * @author HY
 */
@Configuration
public class CacheManagerConfig {
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
            Caffeine.newBuilder().initialCapacity(128).maximumSize(1024).expireAfterWrite(60, TimeUnit.SECONDS));
        return cacheManager;
    }
}
