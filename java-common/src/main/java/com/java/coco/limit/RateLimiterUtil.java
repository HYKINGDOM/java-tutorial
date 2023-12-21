package com.java.coco.limit;

import com.google.common.util.concurrent.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class RateLimiterUtil {


    static Logger logger = LoggerFactory.getLogger(RateLimiterUtil.class);

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(5, 3, TimeUnit.SECONDS);
        long startTimeStamp = System.currentTimeMillis();
        for (int i = 0; i < 15; i++) {
            double time = rateLimiter.acquire();
            logger.info("等待时间:{}s, 总时间:{}ms", time, System.currentTimeMillis() - startTimeStamp);
        }
    }
}
