package com.java.tutorial.project.seq;

import com.java.tutorial.project.async.executor.Async;
import com.java.tutorial.project.async.executor.timer.SystemClock;
import com.java.tutorial.project.async.wrapper.WorkerWrapper;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

/**
 * 串行测试
 *
 * @author meta
 */
@SuppressWarnings("Duplicates")
public class TestSequentialTimeout {

    /**
     * begin-1576719450476 callback worker0 failure--1576719451338----worker0--default-threadName:main callback worker1
     * failure--1576719451338----worker1--default-threadName:main callback worker2
     * failure--1576719451338----worker2--default-threadName:main end-1576719451338 cost-862
     */
    @Test
    public void testFirstTimeout() throws ExecutionException, InterruptedException {
        SeqWorker1 w1 = new SeqWorker1();
        SeqWorker2 w2 = new SeqWorker2();
        SeqTimeoutWorker t = new SeqTimeoutWorker();

        WorkerWrapper<String, String> workerWrapper2 =
            new WorkerWrapper.Builder<String, String>().worker(w2).callback(w2).param("2").build();

        WorkerWrapper<String, String> workerWrapper1 =
            new WorkerWrapper.Builder<String, String>().worker(w1).callback(w1).param("1").next(workerWrapper2).build();

        //2在1后面串行
        //T会超时
        WorkerWrapper<String, String> workerWrapperT =
            new WorkerWrapper.Builder<String, String>().worker(t).callback(t).param("t").next(workerWrapper1).build();

        long now = SystemClock.now();
        System.out.println("begin-" + now);

        Async.beginWork(5000, workerWrapperT);

        System.out.println("end-" + SystemClock.now());
        System.err.println("cost-" + (SystemClock.now() - now));

        Async.shutDown();
    }

}
