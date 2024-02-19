package com.java.tutorial.project.service;

import cn.hutool.core.util.RandomUtil;

import com.java.tutorial.project.util.InheritableThreadLocalUtil;
import com.java.tutorial.project.util.TraceIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.simpl.SimpleThreadPool;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author HY
 */
@Slf4j
@Service
public class ThreadLocalAsyncServiceImpl implements ThreadLocalAsyncService {

    private String target;
    private String params;
    private String method;

    @Async
    @Override
    public void testThreadLocalAsync() {
        log.info("Service层处理线程,testThreadLocalAsync，线程名：{}", Thread.currentThread().getName());
        log.info("TransmittableThreadLocalUtil保存的traceID值为：{}", InheritableThreadLocalUtil.getValue());
        target = RandomUtil.randomString(8);
        params = RandomUtil.randomString(8);
        method = RandomUtil.randomString(8);
    }

    @Async
    @Override
    public void testThreadLocalAsyncThreadLocal() {
        String traceId = TraceIDUtil.getTraceId();
        log.info("Service层处理线程testThreadLocalAsyncThreadLocal，线程名：" + Thread.currentThread().getName());
        log.info(" ThreadLocalUtil保存的traceID值为：{}", traceId);
        target = RandomUtil.randomString(8);
        params = RandomUtil.randomString(8);
        method = RandomUtil.randomString(8);
    }

    //    @Override
    //    public void run() {
    //        TraceIDUtil.setTraceIdByTransmittableThreadLocal();
    //        try {
    //            Thread.sleep(5000L);
    //            log.info("任务线程： {}, 任务执行结束,TraceId: {}, beanName： {}, params： {}, methodName： {}",
    //                    Thread.currentThread().getName(),
    //                    TraceIDUtil.getTraceId(),
    //                    target,
    //                    params,
    //                    method);
    //        } catch (InterruptedException e) {
    //            throw new RuntimeException(e);
    //        }
    //    }
}
