package com.java.cn.thread.pool;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池任务执行完成判断
 */
public class ThreadPoolCompleted {


    public static void main(String[] args) {
        ThreadFactory writeThreadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-Pool-%d").build();
        // 1.创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 20,
                0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1024), writeThreadFactory);

        // 2.添加任务
        addTask(threadPool);
        // 3.判断线程池是否执行完 // 【核心调用方法】
        isCompleted(threadPool);
        // 4.线程池执行完
        System.out.println();
        System.out.println("线程池任务执行完成！");
    }

    /**
     * 方法1：isTerminated 实现方式
     * 判断线程池的所有任务是否执行完
     */
    private static void isCompleted(ThreadPoolExecutor threadPool) {
        threadPool.shutdown();
        while (!threadPool.isTerminated()) {
        }
    }

    /**
     * 方法2：getCompletedTaskCount 实现方式
     * 判断线程池的所有任务是否执行完
     */
    private static void isCompletedByTaskCount(ThreadPoolExecutor threadPool) {
        while (threadPool.getTaskCount() != threadPool.getCompletedTaskCount()) {
        }
    }



    /**
     * 给线程池添加任务
     */
    private static void addTask(ThreadPoolExecutor threadPool) {
        // 任务总数
        final int taskCount = 5;
        // 添加任务
        for (int i = 0; i < taskCount; i++) {
            final int finalI = i;
            threadPool.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 随机休眠 0-4s
                        int sleepTime = new Random().nextInt(5);
                        TimeUnit.SECONDS.sleep(sleepTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("任务%d执行完成%n", finalI);
                }
            });
        }
    }
}
