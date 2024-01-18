package com.java.cn.thread.completable;

import com.java.cn.thread.completable.process.RunProcessA;
import com.java.cn.thread.completable.process.RunProcessB;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {


    @Test
    public void test_demo_01() {

        RunProcessA runProcessA = new RunProcessA();

        RunProcessB runProcessB = new RunProcessB();


        CompletableFuture<Integer> completableFutureA = CompletableFuture.supplyAsync(() -> runProcessA.process(10));


        CompletableFuture<Integer> completableFutureB = CompletableFuture.supplyAsync(() -> runProcessB.process(10));


        completableFutureA.whenComplete((result, exc) -> {

            if (exc == null) {

                System.out.println("completableFutureA: " + result);
            }
        });


        completableFutureB.whenComplete((result, exc) -> {

            if (exc == null) {

                System.out.println("completableFutureB: " + result);
            }
        });


        CompletableFuture<Void> allOf = CompletableFuture.allOf(completableFutureA, completableFutureB);


        allOf.join();


    }
}
