package com.java.cn.thread.completable;

import cn.hutool.core.date.StopWatch;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorCompletionServiceDemoTest {

    /**
     * 公司让你通知大家聚餐 你开车去接人 总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧 研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧 中层管理：我在家上大号
     * 要蹲10分钟就可以出来 你等会来接我吧 都通知完了,等着接吧。 研发：3分钟 我上完大号了。你来接吧 中层管理：10分钟 我上完大号了。你来接吧 总裁：1小时了 我上完大号了。你来接吧 总裁上完大号了，你去接他
     * 研发上完大号了，你去接他 中层管理上完大号了，你去接他
     *
     * @throws Exception
     */
    @Test
    public void test_Executor_demo_01() throws Exception {
        StopWatch testExecutorDemo01 = StopWatch.create("test_Executor_demo_01");
        testExecutorDemo01.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> futureArrayList = new ArrayList<>();
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        Future<String> future10 = executorService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";

        });
        futureArrayList.add(future10);
        Future<String> future3 = executorService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        futureArrayList.add(future3);
        Future<String> future6 = executorService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        futureArrayList.add(future6);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        try {
            for (Future<String> future : futureArrayList) {
                String returnStr = future.get();
                System.out.println(returnStr + "，你去接他");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        testExecutorDemo01.stop();
        System.out.println("test_Executor_demo_01: " + testExecutorDemo01.getTotalTimeSeconds());
    }

    /**
     * 公司让你通知大家聚餐 你开车去接人 总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧 研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧 中层管理：我在家上大号
     * 要蹲10分钟就可以出来 你等会来接我吧 都通知完了,等着接吧。 研发：3分钟 我上完大号了。你来接吧 研发上完大号了，你去接他 中层管理：10分钟 我上完大号了。你来接吧 中层管理上完大号了，你去接他 总裁：1小时了
     * 我上完大号了。你来接吧 总裁上完大号了，你去接他
     *
     * @throws Exception
     */
    @Test
    public void test_Executor_demo_02() throws Exception {
        StopWatch testExecutorDemo02 = StopWatch.create("test_Executor_demo_02");
        testExecutorDemo02.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorCompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        System.out.println("公司让你通知大家聚餐 你开车去接人");
        completionService.submit(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(10);
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        });
        completionService.submit(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            TimeUnit.SECONDS.sleep(6);
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");
        //提交了3个异步任务）
        for (int i = 0; i < 3; i++) {
            String returnStr = completionService.take().get();
            System.out.println(returnStr + "，你去接他");
        }
        testExecutorDemo02.stop();
        System.out.println("test_Executor_demo_02: " + testExecutorDemo02.getTotalTimeSeconds());
    }

    @Test
    public void test_Executor_demo_03() throws Exception {
        StopWatch testExecutorDemo03 = StopWatch.create("test_Executor_demo_03");
        testExecutorDemo03.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("公司让你通知大家聚餐 你开车去接人");

        CompletableFuture<Void> stringCompletableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("总裁：我在家上大号 我最近拉肚子比较慢 要蹲1个小时才能出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("总裁：1小时了 我上完大号了。你来接吧");
            return "总裁上完大号了";
        }).thenAcceptAsync(returnStr -> {
            System.out.println(returnStr + "，你去接他");
        }, executorService);

        CompletableFuture<Void> stringCompletableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("研发：我在家上大号 我比较快 要蹲3分钟就可以出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("研发：3分钟 我上完大号了。你来接吧");
            return "研发上完大号了";
        }).thenAcceptAsync(returnStr -> {
            System.out.println(returnStr + "，你去接他");
        }, executorService);

        CompletableFuture<Void> stringCompletableFuture3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("中层管理：我在家上大号  要蹲10分钟就可以出来 你等会来接我吧");
            try {
                TimeUnit.SECONDS.sleep(6);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("中层管理：10分钟 我上完大号了。你来接吧");
            return "中层管理上完大号了";
        }).thenAcceptAsync(returnStr -> {
            System.out.println(returnStr + "，你去接他");
        }, executorService);

        TimeUnit.SECONDS.sleep(1);
        System.out.println("都通知完了,等着接吧。");

        CompletableFuture<Void> voidCompletableFuture =
            CompletableFuture.allOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3);

        voidCompletableFuture.get();

        testExecutorDemo03.stop();
        System.out.println("test_Executor_demo_03: " + testExecutorDemo03.getTotalTimeSeconds());
    }

}