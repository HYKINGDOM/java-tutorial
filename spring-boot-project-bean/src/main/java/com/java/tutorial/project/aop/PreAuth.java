package com.java.tutorial.project.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PreAuth {

    /**
     *
     *
     * permissionAll()-----只要配置了角色就可以访问
     * hasPermission("MENU.QUERY")-----有MENU.QUERY操作权限的角色可以访问
     * permitAll()-----放行所有请求
     * denyAll()-----只有超级管理员角色才可访问
     * hasAuth()-----只有登录后才可访问
     * hasTimeAuth(1,，10)-----只有在1-10点间访问
     * hasRole(‘管理员’)-----具有管理员角色的人才能访问
     * hasAllRole(‘管理员’,'总工程师')-----同时具有管理员、总工程师角色的人才能访问
     *
     * Spring el
     * 文档地址：https://docs.spring.io/spring/docs/5.1.6.RELEASE/spring-framework-reference/core.html#expressions
     */
    String value();

}

