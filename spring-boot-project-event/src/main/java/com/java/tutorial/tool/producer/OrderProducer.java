package com.java.tutorial.tool.producer;


import com.java.tutorial.tool.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author HY
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final ApplicationContext applicationContext;

    public String sendMessage(String orderId){
        log.info(orderId);
        applicationContext.publishEvent(new OrderEvent(orderId, this));
        return "message send";
    }

}
