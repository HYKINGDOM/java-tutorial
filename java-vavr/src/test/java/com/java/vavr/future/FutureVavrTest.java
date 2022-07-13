package com.java.vavr.future;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

class FutureVavrTest {

    @Test
    public void testFutureSuccess() {
        final var word = "hello world";
        io.vavr.concurrent.Future
                .of(Executors.newFixedThreadPool(1), () -> word)
                .onFailure(throwable -> System.out.println("执行失败方法"))
                .onSuccess(result -> System.out.println("执行成功方法"));
    }

    @Test
    public void testFutureFailure() {
        io.vavr.concurrent.Future
                .of(Executors.newFixedThreadPool(1), () -> {
                    throw new RuntimeException();
                })
                .onFailure(throwable -> System.out.println("执行失败方法"))
                .onSuccess(result -> System.out.println("执行成功方法"));
    }

}