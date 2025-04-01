package com.java.tutorial.project.common;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Expiry;
import org.checkerframework.checker.index.qual.NonNegative;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

public class ReadSourceCodeTest {

    @Test
    public void doReadTimeWheel() {
        Cache<String, String> cache2 = Caffeine.newBuilder()
            //                .expireAfterAccess(5, TimeUnit.SECONDS)
            //                .expireAfterWrite(5, TimeUnit.SECONDS)
            .expireAfter(new Expiry<>() {
                @Override
                public long expireAfterCreate(Object key, Object value, long currentTime) {
                    // 指定过期时间为 Long.MAX_VALUE 则不会过期
                    if ("key0".equals(key)) {
                        return Long.MAX_VALUE;
                    }
                    // 设置条目在创建后 5 秒过期
                    return TimeUnit.SECONDS.toNanos(5);
                }

                // 以下两个过期时间指定为默认 duration 不过期
                @Override
                public long expireAfterUpdate(Object key, Object value, long currentTime, @NonNegative long currentDuration) {
                    return currentDuration;
                }
                @Override
                public long expireAfterRead(Object key, Object value, long currentTime, @NonNegative long currentDuration) {
                    return currentDuration;
                }
            })
            .build();

        cache2.put("key2", "value2");
        System.out.println(cache2.getIfPresent("key2"));
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(cache2.getIfPresent("key2"));
    }

}
