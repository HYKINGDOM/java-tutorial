package com.java.tutorial.project.common.redis;

import java.time.Duration;

import com.java.tutorial.project.common.json.Snack2JsonRedisSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis配置信息
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfigurer implements CachingConfigurer {

    private static JedisPoolConfig getJedisPoolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 连接池中最大连接数
        poolConfig.setMaxTotal(30);
        // 连接池中最大空闲的连接数
        poolConfig.setMaxIdle(30);
        // 连接池中最少空闲的连接数
        poolConfig.setMinIdle(30);
        // 空闲连接的检测周期
        poolConfig.setTimeBetweenEvictionRunsMillis(30000);
        // 连接的最小空闲时间
        poolConfig.setMinEvictableIdleTimeMillis(365);
        // 向连接池借用连接时是否做连接空闲检测, 空闲超时的连接会被移除
        poolConfig.setTestWhileIdle(true);
        // 做空闲连接时, 每次的采样数
        poolConfig.setNumTestsPerEvictionRun(-1);
        // 向连接池借用连接时是否做连接有效性检测, 无效连接会被移除, 每次借用多执行一次ping命令
        poolConfig.setTestOnBorrow(true);
        // 向连接池归还连接时是否做连接有效性检测, 无效连接会被移除, 每次归还多执行一次ping命令
        poolConfig.setTestOnReturn(false);
        // 当连接池用尽后, 调用者是否要等待
        poolConfig.setBlockWhenExhausted(false);
        return poolConfig;
    }

    /**
     * 设置redis数据默认过期时间 设置@cacheable序列化方式
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        SerializationPair<Object> pair =
            RedisSerializationContext.SerializationPair.fromSerializer(new Snack2JsonRedisSerializer());
        return RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair).entryTtl(Duration.ofHours(6));
    }

    @Bean
    public RedisConnectionFactory connectionFactory(RedisProperties properties) {
        JedisPoolConfig poolConfig = getJedisPoolConfig();
        JedisClientConfiguration clientConfig =
            JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).and()
                .readTimeout(Duration.ofSeconds(2)).connectTimeout(Duration.ofSeconds(2)).build();
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setHostName(properties.getHost());
        standaloneConfig.setPort(properties.getPort());
        standaloneConfig.setDatabase(properties.getDatabase());
        standaloneConfig.setPassword(properties.getPassword());

        return new JedisConnectionFactory(standaloneConfig, clientConfig);
    }

    @Bean
    @Primary
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Snack2JsonRedisSerializer());
        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Snack2JsonRedisSerializer());
        template.afterPropertiesSet();

        return template;
    }

}
