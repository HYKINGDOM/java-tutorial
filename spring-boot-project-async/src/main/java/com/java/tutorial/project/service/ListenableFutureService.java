package com.java.tutorial.project.service;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static cn.hutool.core.util.NumberUtil.factorial;


@Component
public class ListenableFutureService {

    @Autowired
    private Executor asyncTaskExecutor;

    public void listenableFuture() throws ExecutionException, InterruptedException {
        ExecutorService threadpool = Executors.newCachedThreadPool();


        ListeningExecutorService service = MoreExecutors.listeningDecorator(threadpool);

        ListenableFuture<BigInteger> guavaFuture = service.submit(() -> factorial(BigInteger.ONE));

        BigInteger result = guavaFuture.get();

    }
}
