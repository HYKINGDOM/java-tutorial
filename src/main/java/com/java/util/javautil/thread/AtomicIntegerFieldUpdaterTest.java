package com.java.util.javautil.thread;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterTest {

    private volatile int a = 0;

    private static final AtomicIntegerFieldUpdater updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "a");

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
