package com.java.tutorial.project.event.factory;

import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.EventFactory;

/**
 *
 * @author meta
 */
public class OrderEventFactory implements EventFactory<OrderEvent> {

    @Override
    public OrderEvent newInstance() {
        return new OrderEvent();
    }
}

