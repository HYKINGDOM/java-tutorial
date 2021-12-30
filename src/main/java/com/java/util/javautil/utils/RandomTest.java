package com.java.util.javautil.utils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class RandomTest {

    private static Random random = new Random();

    private static final int N = 100000;

    //    Random from java.util.concurrent.
    private static class TLRandom implements Runnable {
        @Override
        public void run() {
            double x = 0;
            for (int i = 0; i < N; i++) {
                x += ThreadLocalRandom.current().nextDouble();
            }
        }
    }

    //    Random from java.util
    private static class URandom implements Runnable {
        @Override
        public void run() {
            double x = 0;
            for (int i = 0; i < N; i++) {
                x += random.nextDouble();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("threadNum,Random,ThreadLocalRandom");
        for (int threadNum = 50; threadNum <= 2000; threadNum += 50) {
            ExecutorService poolR = Executors.newFixedThreadPool(threadNum);
            long RStartTime = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                poolR.execute(new URandom());
            }
            try {
                poolR.shutdown();
                poolR.awaitTermination(100, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String str = "" + threadNum + "," + (System.currentTimeMillis() - RStartTime) + ",";

            ExecutorService poolTLR = Executors.newFixedThreadPool(threadNum);
            long TLRStartTime = System.currentTimeMillis();
            for (int i = 0; i < threadNum; i++) {
                poolTLR.execute(new TLRandom());
            }
            try {
                poolTLR.shutdown();
                poolTLR.awaitTermination(100, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(str + (System.currentTimeMillis() - TLRStartTime));
        }
    }
}
