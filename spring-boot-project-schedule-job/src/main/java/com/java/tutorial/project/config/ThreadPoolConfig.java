package com.java.tutorial.project.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description 线程池配置
 * @classname ThreadPoolConfig
 */
@Configuration
public class ThreadPoolConfig {


    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 4 < 256 ? 256 : CORE_POOL_SIZE * 4;

    /**
     * 允许线程空闲时间（单位为秒）
     */
    private static final int KEEP_ALIVE_TIME = 10;

    /**
     * 缓冲队列数
     */
    private static final int QUEUE_CAPACITY = 200;
    /**
     * //线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁
     */
    private static final int AWAIT_TERMINATION = 60;
    /**
     * 用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
     */
    private static final Boolean WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = true;

    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX = "thread-Pool-Task-Executor-";


    /**
     * 线程池配置
     *
     * @return
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        threadPoolTaskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        threadPoolTaskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        threadPoolTaskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        threadPoolTaskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return threadPoolTaskExecutor;
    }


    @Bean(name = "ttlThreadMDCExecutorService")
    public ExecutorService ttlServiceMDCExecutorService() {
        ThreadFactory writeThreadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-ExecutorService-Pool-%d").build();
        BlockingQueue<Runnable> queue = createQueue(QUEUE_CAPACITY);
        TimeUnit seconds = TimeUnit.SECONDS;
        long keepAliveTime = 300L;
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, keepAliveTime, seconds, queue, writeThreadFactory, callerRunsPolicy);
        return TtlExecutors.getTtlExecutorService(threadPoolExecutor);
    }


    @Bean(name = "ttlThreadMDCExecutor")
    public Executor ttlThreadServiceMDCExecutor() {
        String threadNamePrefix = "Thread-Executor-Pool-%d";
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix(threadNamePrefix);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
        taskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return TtlExecutors.getTtlExecutor(taskExecutor);
    }


    /**
     * 执行周期性任务或定时任务
     *
     * @return
     */
    @Bean(name = "scheduledExecutorService")
    public ScheduledExecutorService scheduledExecutorService() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());
        return TtlExecutors.getTtlScheduledExecutorService(scheduledThreadPoolExecutor);
    }





    private BlockingQueue<Runnable> createQueue(int queueCapacity) {
        return (BlockingQueue) (queueCapacity > 0 ? new LinkedBlockingQueue(queueCapacity) : new SynchronousQueue());
    }
}