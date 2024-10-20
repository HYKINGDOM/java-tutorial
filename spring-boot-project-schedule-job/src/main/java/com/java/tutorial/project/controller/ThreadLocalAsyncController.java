package com.java.tutorial.project.controller;

import com.java.tutorial.project.service.ThreadLocalAsyncService;
import com.java.tutorial.project.util.InheritableThreadLocalUtil;
import com.java.tutorial.project.util.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class ThreadLocalAsyncController {

    @Resource
    private ThreadLocalAsyncService threadLocalAsyncService;

    @RequestMapping("/testThreadLocalAsync")
    public String testInheritableThreadLocal() throws Exception {
        log.info("我是Controller层testInheritableThreadLocal处理线程，线程名：{}", Thread.currentThread().getName());
        String traceId = TraceIDUtil.getTraceId();
        InheritableThreadLocalUtil.setValue(traceId);
        log.info(" InheritableThreadLocalUtil保存的值为：{}", InheritableThreadLocalUtil.getValue());
        threadLocalAsyncService.testThreadLocalAsync();
        return traceId;
    }

    @RequestMapping("/testThreadLocalAsyncThreadLocal")
    public String testThreadLocalAsyncThreadLocal() {
        String traceId = TraceIDUtil.getTraceId();
        log.info("我是Controller层testThreadLocalAsyncThreadLocal处理线程，线程名：{}", Thread.currentThread().getName());
        threadLocalAsyncService.testThreadLocalAsyncThreadLocal();
        return traceId;
    }
}
