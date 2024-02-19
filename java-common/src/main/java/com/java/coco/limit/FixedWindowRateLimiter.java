package com.java.coco.limit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 限流算法 - 固定窗口
 *
 * @author meta
 */
public class FixedWindowRateLimiter {
    Logger logger = LoggerFactory.getLogger(FixedWindowRateLimiter.class);

    /**
     * 时间窗口大小，单位毫秒
     */
    long windowSize;

    /**
     * 允许通过的请求数
     */
    int maxRequestCount;

    /**
     * 当前窗口通过的请求数
     */
    AtomicInteger counter = new AtomicInteger(0);

    /**
     * 窗口右边界
     */
    long windowBorder;

    public FixedWindowRateLimiter(long windowSize, int maxRequestCount) {
        this.windowSize = windowSize;
        this.maxRequestCount = maxRequestCount;
        this.windowBorder = System.currentTimeMillis() + windowSize;
    }

    public synchronized boolean tryAcquire() {
        long currentTime = System.currentTimeMillis();
        if (windowBorder < currentTime) {
            logger.info("window reset");
            do {
                windowBorder += windowSize;
            } while (windowBorder < currentTime);
            counter = new AtomicInteger(0);
        }

        if (counter.intValue() < maxRequestCount) {
            counter.incrementAndGet();
            logger.info("tryAcquire success");
            return true;
        } else {
            logger.info("tryAcquire fail");
            return false;
        }
    }
}
