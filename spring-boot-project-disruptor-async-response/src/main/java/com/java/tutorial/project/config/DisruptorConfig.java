package com.java.tutorial.project.config;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.java.tutorial.project.domain.DataEventRequest;
import com.java.tutorial.project.domain.OrderEvent;
import com.java.tutorial.project.event.ResultEventHandler;
import com.java.tutorial.project.event.consumer.DataEventHandlerConsumer;
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


    private static ThreadFactory getThreadFactory(String namePrefix) {
        return ThreadUtil.createThreadFactoryBuilder().setNamePrefix(namePrefix)
            .setDaemon(true).build();
    }

    @Bean
    public Disruptor<OrderEvent> disruptor() {
        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;
        //  create thread factory
        ThreadFactory threadFactory = getThreadFactory("disruptor-order-event-thread-%d");
        // Construct the Disruptor
        Disruptor<OrderEvent> disruptor = new Disruptor<>(new OrderEventFactory(), bufferSize, threadFactory);
        // Connect the handler
        disruptor.handleEventsWith(new OrderEventConsumer());
        // Start the Disruptor, starts all threads running
        disruptor.start();
        return disruptor;
    }

    @Bean
    public Disruptor<DataEventRequest> disruptorConsumerData() {
        int bufferSize = 1024;
        DataEventFactory factory = new DataEventFactory();
        ThreadFactory threadFactory = getThreadFactory("disruptor-consumer-order-event-thread-%d");
        Disruptor<DataEventRequest> disruptor = new Disruptor<>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(new ResultEventHandler());
        disruptor.start();
        return disruptor;
    }

    @Bean
    public RingBuffer<DataEventRequest> consumerData(Disruptor<DataEventRequest> disruptorConsumerData) {
        return disruptorConsumerData.getRingBuffer();
    }

    @Bean
    public Disruptor<DataEventRequest> disruptorProducerData(RingBuffer<DataEventRequest> consumerData) {
        int bufferSize = 1024;
        ThreadFactory threadFactory = getThreadFactory("disruptor-producer-order-event-thread-%d");
        DataEventFactory factory = new DataEventFactory();
        Disruptor<DataEventRequest> disruptor = new Disruptor<>(factory, bufferSize, threadFactory);
        disruptor.handleEventsWith(new DataEventHandlerConsumer(consumerData));
        disruptor.start();
        return disruptor;
    }

    @Bean
    public RingBuffer<DataEventRequest> producerData(Disruptor<DataEventRequest> disruptorProducerData) {
        return disruptorProducerData.getRingBuffer();
    }

}

