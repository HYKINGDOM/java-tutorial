package com.java.coco.system.demo01;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class CountExample {


    /**
     * 5000个请求每次允许100个同时执行
     */
    private static int threadTotal = 100;
    private static int clientTotal = 5000;


    private static long count = 0;

    @Test
    public void test_count_thread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                    log.info("当前总数：{}", count);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            });
        }
        executorService.shutdown();
        log.info("计算得到的总数：{}", count);
    }

    private static void add() {
        count++;
    }
}
