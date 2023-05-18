package com.java.tutorial.tool.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author HY
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(TraceIdConfiguration.class)
public @interface EnableTraceId {
}
