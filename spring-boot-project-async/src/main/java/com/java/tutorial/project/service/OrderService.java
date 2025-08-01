package com.java.tutorial.project.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.java.tutorial.project.domain.Order;
import com.java.tutorial.project.domain.OrderResult;
import com.java.tutorial.project.util.ThreadPoolMonitor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {

    private final ThreadPoolExecutor orderProcessExecutor;
    private final ThreadPoolMonitor threadPoolMonitor;

    public OrderService(ThreadPoolMonitor threadPoolMonitor) {
        this.threadPoolMonitor = threadPoolMonitor;

        // 创建线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,                 // 核心线程数
            50,                 // 最大线程数
            60, TimeUnit.SECONDS, // 空闲线程存活时间
            new LinkedBlockingQueue<>(200), // 工作队列
            new ThreadFactoryBuilder().setNameFormat("order-process-%d").build(),
            new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );

        // 注册到监控系统
        this.orderProcessExecutor = threadPoolMonitor.register("订单处理线程池", executor);
    }

    // 业务方法
    public CompletableFuture<OrderResult> processOrderAsync(Order order) {
        return CompletableFuture.supplyAsync(() -> {
            // 处理订单逻辑
            return doProcessOrder(order);
        }, orderProcessExecutor);
    }

    private OrderResult doProcessOrder(Order order) {
        // 模拟订单处理逻辑
        try {
            Thread.sleep(1000); // 模拟处理时间
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new OrderResult("SUCCESS", "Order processed successfully");
    }
}

