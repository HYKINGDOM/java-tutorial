package com.java.util.javautil.guava;

import com.google.common.cache.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

import java.util.concurrent.TimeUnit;

/**
 * @author HY
 */
@Log
public class GuavaCacheBuilder {


    private LoadingCache<String, String> cache
            //CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
            = CacheBuilder.newBuilder()
            //设置并发级别为8，并发级别是指可以同时写缓存的线程数
            .concurrencyLevel(8)
            //设置写缓存后8秒钟过期
            .expireAfterWrite(8, TimeUnit.SECONDS)
            //设置写缓存后1秒钟刷新
            .refreshAfterWrite(1, TimeUnit.SECONDS)
            //设置缓存容器的初始容量为5
            .initialCapacity(5)
            //设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
            .maximumSize(100)
            //设置要统计缓存的命中率
            .recordStats()
            //设置缓存的移除通知
            .removalListener(notification -> System.out.println(notification.getKey() + " 被移除了，原因： " + notification.getCause()))
            //build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
            .build(
                    new CacheLoader<String, String>() {
                        @Override
                        public String load(String key) {
                            System.out.println("缓存没有时，从数据库加载" + key);
                            return ("tony" + key);
                        }
                    }
            );

    /**
     * 返回是否存在车辆，非null为存在
     *
     * @param vehicleNum
     * @return
     */
    public String get(String vehicleNum) {
         log.info(cache.stats().toString());
        return cache.getIfPresent(vehicleNum);
    }

    /**
     * 返回是否存在车辆，非null为存在
     *
     * @param vehicleNum
     * @return
     */
    public void put(String vehicleNum) {
        cache.put(vehicleNum, "existFlag");
    }
}
