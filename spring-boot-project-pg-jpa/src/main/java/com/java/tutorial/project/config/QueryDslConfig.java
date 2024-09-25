package com.java.tutorial.project.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

/**
 * QueryDsl配置类
 * @author meta
 */
@Configuration
public class QueryDslConfig {

    /**
     * 创建JPAQueryFactory的Bean实例
     * JPAQueryFactory是Querydsl框架中用于构建类型安全的JPA查询的基础对象
     * 该方法通过Spring的@Bean注解标记，表示其返回值应作为一个Bean管理
     *
     * @param entityManager EntityManager是JPA中的核心接口，用于管理实体的持久化、查询及事务控制等
     * @return 返回一个使用给定EntityManager初始化的JPAQueryFactory实例
     *         该实例将被Spring容器管理，并可用于应用程序中的JPA查询
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }

}

