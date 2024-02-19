package com.java.coco.system.demo01;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class MapExample {

    /**
     * 5000个请求每次允许100个同时执行
     */
    private static int threadTotal = 200;
    private static int clientTotal = 5000;

    private static Map<Integer, Integer> map = Maps.newHashMap();

    private static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }

    @Test
    public void test_count_thread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int index = 0; index < clientTotal; index++) {
            final int threadNum = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            });
        }
        executorService.shutdown();
        log.info("计算得到的总数：{}", map.size());
    }

    @Test
    public void test_java_big_decimal() {
        BigDecimal bigDecimal = new BigDecimal("31.1150");
        System.out.println(bigDecimal.setScale(2, RoundingMode.HALF_EVEN));
        BigDecimal bigDecimal01 = new BigDecimal("31.1250");
        System.out.println(bigDecimal01.setScale(2, RoundingMode.HALF_EVEN));
    }

}
