package com.java.tutorial.project.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author WangZY
 * @Date 2020/2/13 15:51
 * @Description 线程池配置
 */
@EnableConfigurationProperties({ToolProperties.class})
@Configuration
public class ThreadTaskPoolConfig {


    @Autowired
    private ToolProperties prop;

    /**
     * 默认CPU密集型--所有参数均需要在压测下不断调整，根据实际的任务消耗时间来设置参数
     * CPU密集型指的是高并发，相对短时间的计算型任务，这种会占用CPU执行计算处理
     * 因此核心线程数设置为CPU核数+1，减少线程的上下文切换，同时做个大的队列，避免任务被饱和策略拒绝。
     */
    @Bean("cpuDenseExecutor")
    public ThreadPoolTaskExecutor cpuDense() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取逻辑可用CPU数
        int logicCpus = Runtime.getRuntime().availableProcessors();
        if (prop.getPoolCpuNumber() != null) {
            //如果是核心业务，需要保活足够的线程数随时支持运行，提高响应速度，因此设置核心线程数为压测后的理论最优值
            executor.setCorePoolSize(prop.getPoolCpuNumber() + 1);
            //设置和核心线程数一致，用队列控制任务总数
            executor.setMaxPoolSize(prop.getPoolCpuNumber() + 1);
            //Spring默认使用LinkedBlockingQueue
            executor.setQueueCapacity(prop.getPoolCpuNumber() * 30);
        } else {
            executor.setCorePoolSize(logicCpus + 1);
            executor.setMaxPoolSize(logicCpus + 1);
            executor.setQueueCapacity(logicCpus * 30);
        }
        //默认60秒，维持不变
        executor.setKeepAliveSeconds(60);
        //使用自定义前缀，方便问题排查
        executor.setThreadNamePrefix(prop.getPoolName());
        //默认拒绝策略，抛异常
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 默认io密集型
     * IO密集型指的是有大量IO操作，比如远程调用、连接数据库
     * 因为IO操作不占用CPU，所以设置核心线程数为CPU核数的两倍，保证CPU不闲下来，队列相应调小一些。
     */
    @Bean("ioDenseExecutor")
    public ThreadPoolTaskExecutor ioDense() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int logicCpus = Runtime.getRuntime().availableProcessors();
        if (prop.getPoolCpuNumber() != null) {
            executor.setCorePoolSize(prop.getPoolCpuNumber() * 2);
            executor.setMaxPoolSize(prop.getPoolCpuNumber() * 2);
            executor.setQueueCapacity(prop.getPoolCpuNumber() * 10);
        } else {
            executor.setCorePoolSize(logicCpus * 2);
            executor.setMaxPoolSize(logicCpus * 2);
            executor.setQueueCapacity(logicCpus * 10);
        }
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix(prop.getPoolName());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        return executor;
    }

    @Bean("cpuForkJoinPool")
    public ForkJoinPool cpuForkJoinPool() {
        int logicCpus = Runtime.getRuntime().availableProcessors();
        return new ForkJoinPool(logicCpus + 1);
    }

    @Bean("ioForkJoinPool")
    public ForkJoinPool ioForkJoinPool() {
        int logicCpus = Runtime.getRuntime().availableProcessors();
        return new ForkJoinPool(logicCpus * 2);
    }

}
