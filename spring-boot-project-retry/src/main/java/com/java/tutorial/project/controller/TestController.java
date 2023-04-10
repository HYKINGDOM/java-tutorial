package com.java.tutorial.project.controller;


import com.java.tutorial.project.service.CustomizeRetryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Resource
    private CustomizeRetryService customizeRetryService;

    @GetMapping(value = "/demo")
    public String test(@RequestParam Integer code) throws Exception {

        return customizeRetryService.testRetry(code);

    }
}
