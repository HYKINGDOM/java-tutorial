package com.java.tutorial.project.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.java.tutorial.project.domain.DataEventRequest;
import com.java.tutorial.project.domain.OrderEvent;
import com.java.tutorial.project.event.DataEventHandler;
import com.java.tutorial.project.event.ResultEventHandler;
import com.java.tutorial.project.event.consumer.OrderEventConsumer;
import com.java.tutorial.project.event.factory.DataEventFactory;
import com.java.tutorial.project.event.factory.OrderEventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadFactory;

/**
 * @author meta
 */
@Configuration
public class DisruptorConfig {

    @Bean
    public Disruptor<OrderEvent> disruptor() {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        ThreadFactoryBuilder threadFactoryBuilder = ThreadUtil.createThreadFactoryBuilder();
        ThreadFactory threadFactory = threadFactoryBuilder.setNamePrefix("disruptor-order-event-thread-").build();

        // Construct the Disruptor
        Disruptor<OrderEvent> disruptor = new Disruptor<>(new OrderEventFactory(), bufferSize, threadFactory);

        // Connect the handler
        disruptor.handleEventsWith(new OrderEventConsumer());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        return disruptor;
    }

    @Bean
    public Disruptor<DataEventRequest> disruptorB() {
        int bufferSize = 1024;
        DataEventFactory factory = new DataEventFactory();
        ThreadFactory threadFactory =
            ThreadUtil.createThreadFactoryBuilder().setNamePrefix("disruptor-B-order-event-thread-%d").setDaemon(true)
                .build();
        Disruptor<DataEventRequest> disruptor = new Disruptor<>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(new ResultEventHandler());
        disruptor.start();
        return disruptor;
    }

    @Bean
    public RingBuffer<DataEventRequest> ringBufferB(Disruptor<DataEventRequest> disruptorB) {
        return disruptorB.getRingBuffer();
    }

    @Bean
    public Disruptor<DataEventRequest> disruptorA(RingBuffer<DataEventRequest> ringBufferB) {
        int bufferSize = 1024;
        ThreadFactory threadFactory =
            ThreadUtil.createThreadFactoryBuilder().setNamePrefix("disruptor-A-order-event-thread-%d").setDaemon(true)
                .build();
        DataEventFactory factory = new DataEventFactory();
        Disruptor<DataEventRequest> disruptor = new Disruptor<>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(new DataEventHandler(ringBufferB));
        disruptor.start();
        return disruptor;
    }

    @Bean
    public RingBuffer<DataEventRequest> ringBufferA(Disruptor<DataEventRequest> disruptorA) {
        return disruptorA.getRingBuffer();
    }

}

