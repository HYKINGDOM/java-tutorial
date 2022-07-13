package com.java.vavr.future;

import io.vavr.concurrent.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class FutureVavr {

    public static void main(String[] args) {
        Future.of(Executors.newFixedThreadPool(1), () -> "toJava").toCompletableFuture();

        Future.fromCompletableFuture(CompletableFuture.runAsync(() -> {
        }));
    }
}
