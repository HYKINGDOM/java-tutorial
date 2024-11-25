package com.java.tutorial.project.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.project.service.DisruptorEventPublisher;
import com.java.tutorial.project.service.DisruptorEventPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

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


    private final AtomicInteger goodsCount = new AtomicInteger();


    @PostConstruct
    public void initData(){
        goodsCount.set(2);
    }

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
            return "hello " + getGoodsCount();
        };
    }

    /**
     * webAsyncTask api
     *
     * @return
     */
    @GetMapping(value = "/webAsyncTask", produces = MediaType.TEXT_PLAIN_VALUE)
    public WebAsyncTask<String> webAsyncTask() {
        WebAsyncTask<String> result = new WebAsyncTask<>(30003, () -> {
            Thread.sleep(2000);
            return "hello " + getGoodsCount();
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
     * testSetDeferredResult API 该方法使用 DeferredResult 异步处理请求。 特别适用于 API 请求处理时间较长的情况，允许服务器在等待结果的同时继续执行其他任务。
     *
     * @param key 用于从 map 中检索或创建 DeferredResult 对象的唯一标识符。 此参数对于识别请求并获取相应结果至关重要。
     * @return 返回一个字符串结果。 具体内容是 key 加上随机生成的 20 个字符的字符串。 这种设计确保了返回值的唯一性和随机性。
     */
    @GetMapping("/testSetDeferredResult")
    public String testSetDeferredResult(@RequestParam String key) {

        // 根据 key 从 map 中检索或创建一个 DeferredResult 对象
        // 如果对应 key 的 DeferredResult 不存在，则创建一个新的 DeferredResult 并添加到 map 中
        // 这一步确保每个请求都有一个与之关联的唯一的 DeferredResult
        DeferredResult<String> stringDeferredResult = deferredResultMap.computeIfAbsent(key, s -> {
            // 创建一个新的 DeferredResult 实例
            // 将结果设置为 key 加上随机生成的 20 个字符的字符串，以确保结果的唯一性
            DeferredResult<String> deferredResult = new DeferredResult<>();
            deferredResult.setResult(getGoodsCount());

            // 模拟耗时操作，休眠 2 秒
            // 目的是展示 DeferredResult 的异步特性
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                log.error("错误", e);
            }

            return deferredResult;
        });

        // 返回 DeferredResult 的结果
        // 使用 Objects.requireNonNull 确保结果不为空
        return Objects.requireNonNull(stringDeferredResult.getResult()).toString();
    }

    /**
     * 使用ResponseBodyEmitter处理请求的方法 该方法用于当需要异步处理请求并实时发送数据时
     * 接口调用之后一直被阻塞emitter.send(msg);执行之后消息没有返回,而是要等循环10次彻底结束之后才会显示所有结果
     *
     * @return ResponseBodyEmitter 实例，用于发送数据和控制流
     */
    @GetMapping(value = "/bodyEmitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter handle(HttpServletResponse response) {
        // 创建一个ResponseBodyEmitter，-1代表不超时
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(-1L);
        // 异步执行耗时操作
        CompletableFuture.runAsync(() -> {
            try {
                // 模拟耗时操作
                for (int i = 0; i < 10; i++) {
                    // 发送数据
                    String msg = "bodyEmitter " + i + " @ " + new Date() + "\n";
                    emitter.send(msg);
                    log.info(msg);
                    Thread.sleep(2000);
                    log.info("bodyEmitter " + i);
                }
                // 完成
                emitter.complete();
            } catch (Exception e) {
                // 发生异常时结束接口
                emitter.completeWithError(e);
            }
        });
        return emitter;
    }

    /**
     * 使用SseEmitter进行消息流的返回 该方法用于当需要异步处理请求并实时发送数据时
     *
     * @return
     */
    @GetMapping(value = "/bodySseEmitter", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter handleSseEmitter() {
        // 创建一个SseEmitter，-1代表不超时
        SseEmitter emitter = new SseEmitter(-1L);
        // 异步执行耗时操作
        CompletableFuture.runAsync(() -> performEmitterTask(emitter));
        return emitter;
    }

    private void performEmitterTask(SseEmitter emitter) {

        CompletableFuture.runAsync(() -> {
            try {
                // 模拟耗时操作
                for (int i = 0; i < 10; i++) {
                    // 发送数据
                    String msg = "bodyEmitter " + i + " @ " + new Date();
                    emitter.send(msg);
                    log.info(msg);
                    Thread.sleep(2000);
                    log.info("bodyEmitter " + i);
                }
                // 完成 如果不调用complete方法，则客户端会一直等待数据
                //emitter.complete();
            } catch (Exception e) {
                // 发生异常时结束接口
                emitter.completeWithError(e);
            }
        });
    }

    private String getGoodsCount() {

        String msg;
        if (goodsCount.get() != 0){
            goodsCount.incrementAndGet();
            msg = "订单生成成功: " + IdUtil.fastSimpleUUID() + " 剩余数量:" + goodsCount.get();
        }else {
            msg = "订单生成失败: " + " 剩余数量:" + goodsCount.get();
        }
        log.info(msg);
        return msg;
    }

}
