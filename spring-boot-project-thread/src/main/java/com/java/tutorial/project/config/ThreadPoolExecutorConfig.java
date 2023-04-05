package com.java.tutorial.project.config;


import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
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
 * @author HY
 */
@Slf4j
@Configuration
public class ThreadPoolExecutorConfig {


    /**
     * cpu 核心数
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    /**
     * 核心线程数量大小
     */
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    /**
     * 线程池最大容纳线程数
     */
    private static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 4 < 256 ? 256 : CORE_POOL_SIZE * 4;

    /**
     * 阻塞队列
     */
    private static final int WORK_QUEUE = 20;
    /**
     * 线程空闲后的存活时长
     */
    private static final int KEEP_ALIVE_TIME = 30;

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
     * ThreadPoolTaskExecutor spring 封装线程池
     *
     * 当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略
     * 通常有以下四种策略：
     * ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：重试添加当前的任务，自动重复调用 execute() 方法，直到成功
     */
    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        //线程池维护线程的最大数量,只有在缓冲队列满了之后才会申请超过核心线程数的线程
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        //缓存队列
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        //许的空闲时间,当超过了核心线程出之外的线程在空闲时间到达之后会被销毁
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        //方法内部线程名称
        taskExecutor.setThreadNamePrefix("thread-Pool-Task-Executor-%d");
        // 线程池对拒绝任务(无线程可用)的处理策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }


    /**
     * ThreadPoolExecutor
     * @return ThreadPoolExecutor
     */
    @Bean(name = "defaultThreadPoolExecutor", destroyMethod = "shutdown")
    public ThreadPoolExecutor systemCheckPoolExecutorService() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadFactoryBuilder().setNameFormat("default-executor-%d").build(),
                (r, executor) -> log.error("defaultThreadPoolExecutor pool is full! "));
    }


    /**
     * 线程池配置
     */
    @Bean(name = "threadPoolExecutor")
    public ExecutorService taskExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-Pool-Executor-%d").build();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        return new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, createQueue(QUEUE_CAPACITY), threadFactory, callerRunsPolicy);
    }


    @Bean(name = "ttlThreadPoolExecutor")
    public ExecutorService ttlThreadPoolExecutorService() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("ttl-Thread-Pool-Executor-%d").build();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, createQueue(QUEUE_CAPACITY), threadFactory, callerRunsPolicy);
        return TtlExecutors.getTtlExecutorService(threadPoolExecutor);
    }


    @Bean(name = "ttlThreadPoolTaskExecutor")
    public Executor ttlThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_TIME);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setThreadNamePrefix("ttl-Thread-Pool-Task-Executor-%d");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN);
        taskExecutor.setAwaitTerminationSeconds(AWAIT_TERMINATION);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize();
        return TtlExecutors.getTtlExecutor(taskExecutor);
    }


    /**
     * 执行周期性任务或定时任务
     *
     */
    @Bean(name = "defaultScheduledExecutorService")
    public ScheduledExecutorService defaultScheduledExecutorService() {
        return new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, new BasicThreadFactory.Builder().namingPattern("Scheduled-Executor-Service-%d").daemon(true).build());
    }


    /**
     * 执行周期性任务或定时任务
     *
     * @return
     */
    @Bean(name = "ttlScheduledExecutorService")
    public ScheduledExecutorService ttlScheduledExecutorService() {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, new BasicThreadFactory.Builder().namingPattern("ttl-Scheduled-Executor-Service-%d").daemon(true).build());
        return TtlExecutors.getTtlScheduledExecutorService(scheduledThreadPoolExecutor);
    }


    private BlockingQueue<Runnable> createQueue(int queueCapacity) {
        return (BlockingQueue) (queueCapacity > 0 ? new LinkedBlockingQueue<>(queueCapacity) : new SynchronousQueue());
    }

}
