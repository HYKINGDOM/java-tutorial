package com.java.tutorial.project.config;

import com.java.coco.utils.RanDomUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncTaskExecutor {

    @Bean("mvcAsyncTaskExecutor")
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 线程池维护线程的最少数量
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() + 1);
        executor.setCorePoolSize(5);
        // 线程池维护线程的最大数量
        executor.setMaxPoolSize(10);
        // 线程池所使用的缓冲队列
        executor.setQueueCapacity(10);
        executor.prefersShortLivedTasks();
        executor.setThreadNamePrefix("fyk-mvcAsyncTask-Thread-");
        executor.setBeanName("TaskId-" + RanDomUtils.randomNumeric(5));
        executor.setKeepAliveSeconds(20);
        //调用者执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        // 线程全部结束才关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 如果超过60s还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(30);
        executor.initialize();

        return executor;
    }
}
