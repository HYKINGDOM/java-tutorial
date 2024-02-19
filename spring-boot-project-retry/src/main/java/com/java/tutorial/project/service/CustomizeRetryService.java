package com.java.tutorial.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

/**
 * @author HY
 */
@Slf4j
@Service
public class CustomizeRetryService {

    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String testRetry(int code) throws Exception {
        if (0 == code) {
            throw new Exception("失败调用");
        }
        log.info("方法执行成功");
        return "success";
    }

    @Recover
    public String recover(Exception e, int code) {
        log.info("recover 方法执行");
        return "500";
    }
}
