package com.java.tutorial.project.config.event;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.java.tutorial.project.domain.OrderEvent;
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
        disruptor.handleEventsWith(new OrderEventHandler());

        // Start the Disruptor, starts all threads running
        disruptor.start();

        return disruptor;
    }
}

