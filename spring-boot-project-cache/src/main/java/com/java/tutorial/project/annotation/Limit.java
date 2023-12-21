package com.java.tutorial.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author meta
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface Limit {

    /**
     * 资源主键
     * @return
     */
    String key() default "";

    /**
     * 最多访问次数,代表请求总数量
     * @return
     */
    double permitsPerSeconds();

    /**
     * 时间：即timeout时间内,只允许有permitsPerSeconds个请求总数量访问，超过的将被限制不能访问
     * @return
     */
    long timeout();

    /**
     * 时间类型
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.MILLISECONDS;

    /**
     * 提示信息
     * @return
     */
    String msg() default "系统繁忙,请稍后重试";
}