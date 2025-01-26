package com.java.tutorial.project.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 通用线程池,对于使用频次比较低的,异步执行不需要返回结果的,可以使用此方法
 *
 * @author kscs
 */
@Configuration
@EnableAsync
public class CommonThreadManage {
    /**
     * 日志服务
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonThreadManage.class);
    /**
     * 线程数量
     */
    private static final int THREAD_COUNT = 100;
    /**
     * 线程数量
     */
    private static final int THREAD_MAX_COUNT = 150;
    /**
     * 线程数量最大任务队列数量
     */
    private static final int THREAD_TASK_MAX_COUNT = 1000;

    /**
     * 异步线程配置
     *
     * @return 返回线程池配置
     */
    @Bean
    public Executor asyncCommonExecutor() {
        logger.info("start asyncCommonExecutor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(THREAD_COUNT);
        //配置最大线程数
        executor.setMaxPoolSize(THREAD_MAX_COUNT);
        //配置队列大小
        executor.setQueueCapacity(THREAD_TASK_MAX_COUNT);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-hotel-common-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}

