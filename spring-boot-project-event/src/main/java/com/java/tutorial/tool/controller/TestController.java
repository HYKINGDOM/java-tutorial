package com.java.tutorial.tool.controller;

import com.java.tutorial.tool.domain.TestProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {


    @Autowired
    private TestProperties testProperties;

    @GetMapping("/test1")
    public Object test1(@RequestParam String name) {
        return testProperties.getByName(name);
    }
}
