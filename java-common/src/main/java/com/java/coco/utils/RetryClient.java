package com.java.coco.utils;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 重试操作
 *
 * @author meta
 */
@Slf4j
public class RetryClient {

    /**
     * 最大重试次数
     */
    private static final int ATTEMPT_NUMBER = 3;

    private static Retryer<Boolean> getBooleanRetryV2(int attemptNumber) {
        return RetryerBuilder.<Boolean>newBuilder()
            // 设置异常重试源
            .retryIfExceptionOfType(Exception.class)
            //设置根据结果重试
            .retryIfResult(res -> res == false)
            // 设置等待间隔时间，使用指数退避策略
            .withWaitStrategy(WaitStrategies.exponentialWait(1000, 2, TimeUnit.SECONDS))
            // 设置最大重试次数
            .withStopStrategy(StopStrategies.stopAfterAttempt(attemptNumber))
            // 当发生重试之后记录日志
            .withRetryListener(new RetryListener() {
                @Override
                public <V> void onRetry(Attempt<V> attempt) {
                    if (attempt.hasException()) {
                        log.warn("重试上传OSS，第 {} 次重试，异常信息: {}", attempt.getAttemptNumber(),
                            attempt.getExceptionCause().getMessage());
                    } else {
                        log.warn("重试上传OSS，第 {} 次重试", attempt.getAttemptNumber());
                    }
                }
            }).build();
    }

    /**
     * 方法重试
     *
     * @param param          请求参数
     * @param objectFunction 方法
     * @param attemptNumber  重试次数
     * @return
     */
    public boolean retryUploadOss(Object param, Function<Object, Object> objectFunction, int attemptNumber) {

        Retryer<Boolean> retryer = getBooleanRetryV2(attemptNumber);

        try {
            retryer.call(() -> {

                Object apply = objectFunction.apply(param);

                return !ObjectUtil.isEmpty(apply);
            });
        } catch (Exception e) {
            log.error(ExceptionUtil.getRootCauseMessage(e));
        }
        return true;
    }
}
