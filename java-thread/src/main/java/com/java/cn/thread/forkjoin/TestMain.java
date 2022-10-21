package com.java.cn.thread.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinTask;

public class TestMain {

    public static void main(String[] args) {
        Instant start = Instant.now();
        ForkJoinTask<Long> task = new ForkJoinSumCalculate(0L, 1500000000L);
        Long sum = ForkJoinPoolUtils.invokeInDedicatedThreadPool(task);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println("耗费时间为：" + Duration.between(start, end).toMillis());
    }

}
