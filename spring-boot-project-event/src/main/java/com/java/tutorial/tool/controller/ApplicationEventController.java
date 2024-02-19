package com.java.tutorial.tool.controller;

import cn.hutool.core.util.RandomUtil;
import com.java.tutorial.tool.producer.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class ApplicationEventController {

    @Autowired
    private OrderProducer orderProducer;

    @GetMapping("/send")
    public void sendEvent() {
        orderProducer.sendMessage(RandomUtil.randomString(13));
    }
}
