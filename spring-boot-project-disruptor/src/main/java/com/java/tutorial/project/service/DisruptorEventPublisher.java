package com.java.tutorial.project.service;

import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisruptorEventPublisher {

    private final RingBuffer<OrderEvent> ringBuffer;

    @Autowired
    public DisruptorEventPublisher(Disruptor<OrderEvent> disruptor) {
        this.ringBuffer = disruptor.getRingBuffer();
    }

    public void publishEvent(long value) {
        // Grab the next sequence
        long sequence = ringBuffer.next();
        try {
            // Get the entry in the Disruptor
            OrderEvent event = ringBuffer.get(sequence);
            // Fill with data
            event.setValue(value);
            event.setTractId(TraceIDUtil.getTraceId());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}

