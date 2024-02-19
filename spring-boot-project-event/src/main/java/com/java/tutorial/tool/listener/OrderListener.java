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
        String traceId = event.getTraceId();
        log.info("message traceId: {}", traceId);

        if ("test".equals(traceId)) {
            throw new RuntimeException();
        }
    }
}
