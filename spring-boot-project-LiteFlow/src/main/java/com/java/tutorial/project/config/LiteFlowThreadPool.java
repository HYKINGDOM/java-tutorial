package com.java.tutorial.project.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yomahub.liteflow.thread.ExecutorBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * liteflow 线程池
 *
 * @author meta
 */
public class LiteFlowThreadPool implements ExecutorBuilder {

    @Override
    public ExecutorService buildExecutor() {
        ThreadFactory threadFactory =
            new ThreadFactoryBuilder().setNameFormat("liteflow-thread-pool-%s").setDaemon(true).build();

        ThreadTaskDecoratorFactory copyingThreadFactory = new ThreadTaskDecoratorFactory(threadFactory);

        return new ThreadPoolExecutor(
            // 核心线程数，即2个常开窗口
            2,
            // 最大的线程数，银行所有的窗口
            5,
            // 空闲时间
            5, TimeUnit.SECONDS,
            // 工作队列
            new LinkedBlockingQueue<>(5),
            // 线程工厂
            copyingThreadFactory,
            // 拒绝策略
            new ThreadPoolExecutor.AbortPolicy());

    }

}
