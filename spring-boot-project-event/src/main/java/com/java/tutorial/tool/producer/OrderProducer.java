package com.java.tutorial.tool.producer;

import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.tool.domain.MessageDto;
import com.java.tutorial.tool.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author meta
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    //    private final ApplicationContext applicationContext;

    private final ApplicationEventPublisher applicationEventPublisher;

    public String sendMessage(String orderId) {
        log.info(orderId);

        String traceId = TraceIDUtil.getTraceId();
        log.info("Producer: {}", traceId);
        OrderEvent orderEvent = new OrderEvent(traceId, orderId, "123", LocalDateTime.now(), this);

        applicationEventPublisher.publishEvent(orderEvent);
        return "message send";
    }

}
