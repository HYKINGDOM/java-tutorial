package com.java.util.javautil.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {
        for (int i = 0; i < 200; i++) {
            Thread t = new Thread(() -> atomicInteger.incrementAndGet());
            t.start();
            System.out.println(atomicInteger);
        }
    }
}
