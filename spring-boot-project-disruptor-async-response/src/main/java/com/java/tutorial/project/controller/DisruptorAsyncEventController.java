package com.java.tutorial.project.controller;

import com.java.tutorial.project.service.DisruptorEventPublisher;
import com.java.tutorial.project.service.DisruptorEventPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 测试Disruptor异步响应接口
 *
 * @author meta
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class DisruptorAsyncEventController {

    @Resource
    private DisruptorEventPublisher disruptorEventPublisher;

    @Resource
    private DisruptorEventPublisherService disruptorEventPublisherService;

    /**
     * 定义一个全局的变量，用来存储DeferredResult对象
     */
    private Map<String, DeferredResult<String>> deferredResultMap = new ConcurrentHashMap<>();

    /**
     * 发送消息-demo-01
     *
     * @param msg
     * @return
     */
    @GetMapping("/send-msg-data")
    public String send01(Long msg) {
        disruptorEventPublisher.publishEvent(msg);
        return "success";
    }

    /**
     * 发送消息-demo-02
     *
     * @param msg
     * @return
     */
    @GetMapping("/send-msg-data-02")
    public String send02(Long msg) {
        disruptorEventPublisherService.publishEvent(msg);
        return "success";
    }

    /**
     * Callable api
     *
     * @return
     */
    @GetMapping("/testCallAble")
    public Callable<String> testCallAble() {
        return () -> {
            Thread.sleep(40000);
            return "hello " + RandomStringUtils.randomAlphabetic(5);
        };
    }

    /**
     * webAsyncTask api
     *
     * @return
     */
    @GetMapping("/webAsyncTask")
    public WebAsyncTask<String> webAsyncTask() {
        WebAsyncTask<String> result = new WebAsyncTask<>(30003, () -> {
            return "hello " + RandomStringUtils.randomAlphabetic(5);
        });
        result.onTimeout(() -> {
            log.info("timeout callback");
            return "timeout callback " + RandomStringUtils.randomAlphabetic(5);
        });
        result.onCompletion(() -> log.info("finish callback"));
        return result;
    }

    /**
     * DeferredResult api
     *
     * @return
     */
    @GetMapping("/testDeferredResult")
    public DeferredResult<String> testDeferredResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        deferredResultMap.put("test", deferredResult);
        return deferredResult;
    }

    /**
     * testSetDeferredResult api
     * @return
     */
    @GetMapping("/testSetDeferredResult")
    public String testSetDeferredResult() {
        DeferredResult<String> deferredResult = deferredResultMap.get("test");
        boolean flag = deferredResult.setResult("testSetDeferredResult");
        if (!flag) {
            log.info("结果已经被处理，此次操作无效");
        }
        return "ok";
    }
}
