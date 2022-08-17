package com.java.tutorial.tool.listener;

import com.java.tutorial.tool.event.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


/**
 * @author HY
 */
@Slf4j
@Component
public class OrderListener implements ApplicationListener<OrderEvent> {

    @Override
    public void onApplicationEvent(OrderEvent event) {
        String orderId = event.getOrderId();
        log.info("message orderId:{}", orderId);

        if ("test".equals(orderId)) {
            throw new RuntimeException();
        }
    }
}
