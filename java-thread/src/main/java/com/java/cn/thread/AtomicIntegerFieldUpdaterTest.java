package com.java.cn.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    private static final AtomicIntegerFieldUpdater updater =
        AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "a");
    private volatile int a = 0;

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();
        for (int i = 0; i < 200; i++) {
            final int j = i;
            Thread t = new Thread(() -> {
                System.out.println("i=" + j + ", a=" + updater.incrementAndGet(test));
            });
            t.start();
        }
    }
}
