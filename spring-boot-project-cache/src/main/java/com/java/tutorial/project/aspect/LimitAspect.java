package com.java.tutorial.project.aspect;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.java.tutorial.project.annotation.Limit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Aspect
@Component
public class LimitAspect {
    Logger logger = LoggerFactory.getLogger(LimitAspect.class);
    private final Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    @Around("@annotation(com.java.tutorial.project.annotation.Limit)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //拿limit的注解
        Limit limit = method.getAnnotation(Limit.class);
        if (limit != null) {
            // key作用：不同的接口，不同的流量控制
            String key = limit.key();
            RateLimiter rateLimiter;
            //验证缓存是否有命中key
            if (!limitMap.containsKey(key)) {
                //创建令牌桶
                rateLimiter = RateLimiter.create(limit.permitsPerSeconds());
                limitMap.put(key, rateLimiter);
                logger.info("新建了令牌桶={},容量={}", key, limit.permitsPerSeconds());
            }
            rateLimiter = limitMap.get(key);
            //拿令牌
            boolean acquire = rateLimiter.tryAcquire(limit.timeout(), limit.timeUnit());
            //拿不到令牌，直接返回异常信息
            if (!acquire) {
                logger.debug("令牌桶={},获取令牌失败", key);
                throw new RuntimeException(limit.msg());
            }
        }
        return joinPoint.proceed();
    }
}
