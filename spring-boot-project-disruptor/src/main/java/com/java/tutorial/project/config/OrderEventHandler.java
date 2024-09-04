package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 * @author meta
 */
public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) {
        System.out.println("消费者: " + event.getValue());
    }
}
