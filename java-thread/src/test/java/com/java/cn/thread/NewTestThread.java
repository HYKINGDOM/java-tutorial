package com.java.util.javautil.scs.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

public class NewTestThread {

    private ThreadFactory writeThreadFactory = new ThreadFactoryBuilder().setNameFormat("Thread-Pool-%d").build();

    public ExecutorService singleThreadPool = new ThreadPoolExecutor(1,3,0L,
            TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>(1024),writeThreadFactory,new ThreadPoolExecutor.AbortPolicy());
}
