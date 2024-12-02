package com.java.tutorial.project.config;

import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.ThreadFactory;

/**
 * @author meta
 */
public class ThreadTaskDecoratorFactory implements ThreadFactory {
    private final ThreadFactory delegate;

    public ThreadTaskDecoratorFactory(ThreadFactory delegate) {
        this.delegate = delegate;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        Runnable wrappedRunnable = () -> {
            try {
                MDC.setContextMap(contextMap);
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
        return delegate.newThread(wrappedRunnable);
    }
}

