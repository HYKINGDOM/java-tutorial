package com.java.cn.thread.completable;

import org.junit.jupiter.api.Test;

import static com.java.cn.thread.completable.ExcutorCompletableFuture.runAsync;
import static com.java.cn.thread.completable.ExcutorCompletableFuture.supplyAsync;
import static com.java.cn.thread.completable.ExcutorCompletableFuture.whenComplete;
import static com.java.cn.thread.completable.ExcutorCompletableFuture.*;
import static org.junit.jupiter.api.Assertions.*;

class ExcutorCompletableFutureTest {

    @Test
    void test_test_runAsync() throws Exception {
        runAsync();
    }

    @Test
    void test_supplyAsync() throws Exception {
        supplyAsync();
    }

    @Test
    void test_whenComplete() throws Exception {
        whenComplete();
    }

    @Test
    void test_thenApply() throws Exception {
        thenApply();
    }

    @Test
    void test_handle() throws Exception {
        handle();
    }

    @Test
    void test_thenAccept() throws Exception {
        thenAccept();
    }

    @Test
    void test_thenAcceptAsync() throws Exception {
        thenAcceptAsync();
    }

    @Test
    void test_thenRun() throws Exception {
        thenRun();
    }

    @Test
    void test_thenCombine() throws Exception {
        thenCombine();
    }

    @Test
    void test_thenAcceptBoth() throws Exception {
        thenAcceptBoth();
    }

    @Test
    void test_applyToEither() throws Exception {
        applyToEither();
    }

    @Test
    void test_acceptEither() throws Exception {
        acceptEither();
    }

    @Test
    void test_runAfterEither() throws Exception {
        runAfterEither();
    }

    @Test
    void test_acceptEitherAsync() throws Exception {
        acceptEitherAsync();
    }

    @Test
    void test_runAfterBoth() throws Exception {
        runAfterBoth();
    }

    @Test
    void test_thenCompose() throws Exception {
        thenCompose();
    }
}