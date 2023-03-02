package com.java.tutorial.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatSubmit {

    /**
     * 防重复操作限时标记数值（存储redis限时标记数值）
     */
    String value() default "value";

    /**
     * 防重复操作过期时间（借助redis实现限时控制）
     */
    long expireSeconds() default 10;
}
