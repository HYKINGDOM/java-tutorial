package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Service
public class DisruptorEventPublisherService {

    @Resource
    private Disruptor<OrderEvent> disruptor;

    public void publishEvent(long orderNum) {

        EventTranslator<OrderEvent> translator = new EventTranslator() {
            @Override
            public void translateTo(Object o, long l) {
                OrderEvent event = (OrderEvent)o;
                event.setValue(orderNum);
            }
        };
        disruptor.publishEvent(translator);
    }
}

