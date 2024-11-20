package com.java.tutorial.project.controller;

import com.java.tutorial.project.service.DisruptorEventPublisher;
import com.java.tutorial.project.service.DisruptorEventPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
}
