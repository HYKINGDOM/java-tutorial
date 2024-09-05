package com.java.tutorial.project.controller;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author meta
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TestDemoController {

    @GetMapping("/test/{age}")
    public String test(@PathVariable Integer age, @RequestParam String name) {
        log.info("test process:{},age:{}", name, age);
        boolean safeSleep = ThreadUtil.safeSleep(1000);
        log.info("test process:{},name: {}", safeSleep, name);
        return "test" + safeSleep;
    }
}
