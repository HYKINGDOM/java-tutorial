package com.java.util.javautil.thread.executors;

import java.util.concurrent.*;

public class Wanger {

    public static void main(String[] args) {
        Wanger wanger = new Wanger();
        wanger.runThreadPoolExecutor();
    }


    public void runThreadPoolExecutor(){
        ExecutorService executorService = new ThreadPoolExecutor(10, 10,
                60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue(10));
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> System.out.println("我叫" + Thread.currentThread().getName() + "，使用推荐线程池的方式"));
            executorService.execute(thread);
        }
        executorService.shutdown();
    }


    public void runExecutors(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("我叫" + Thread.currentThread().getName() + "，使用线程池的方式");
                }
            });
            executorService.execute(thread);
        }
        executorService.shutdown();
    }



    public void runAbleThread(){
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("我叫" + Thread.currentThread().getName() + "，使用Runable的方式");
                }
            });
            thread.start();
        }
    }
}
