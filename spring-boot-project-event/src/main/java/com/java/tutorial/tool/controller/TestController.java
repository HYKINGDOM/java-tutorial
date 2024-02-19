package com.java.tutorial.tool.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.tutorial.tool.domain.TestDemoProperties;
import com.java.tutorial.tool.domain.TestProperties;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestProperties testProperties;

    @Resource
    private TestDemoProperties testDemoProperties;

    @GetMapping("/test1")
    public Object test1(@RequestParam String name) {
        return testProperties.getByName(name);
    }

    @GetMapping("/test2")
    public Object test2(@RequestParam String name) {
        return testDemoProperties.isConfigs();
    }

    @GetMapping("/test3")
    public Object test3(@RequestParam String name) {
        return testDemoProperties.getAAA();
    }
}
