package com.java.tutorial.project.controller;

import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.service.DisruptorEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class DisruptorEventController {

    @Resource
    private DisruptorEventPublisher disruptorEventPublisher;

    @GetMapping("/send")
    public String send(Long msg) {
        log.info("send msg :{}", TraceIDUtil.getTraceId());
        disruptorEventPublisher.publishEvent(msg);
        return "success";
    }
}
