package com.java.tutorial.project;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.java.tutorial.project.config.event.OrderEventFactory;
import com.java.tutorial.project.config.event.OrderEventConsumer;
import com.java.tutorial.project.config.event.OrderEventProducer;
import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadFactory;

public class SpringBootProjectApplicationTests {

    @Test
    public void test_disruptor_demo() {

        OrderEventFactory orderEventFactory = new OrderEventFactory();
        int ringBufferSize = 4;

        ThreadFactoryBuilder threadFactoryBuilder = ThreadUtil.createThreadFactoryBuilder();
        ThreadFactory threadFactory = threadFactoryBuilder.setNamePrefix("test-thread-").build();

        /**
         * 1. 实例化disruptor对象
         1) eventFactory: 消息(event)工厂对象
         2) ringBufferSize: 容器的长度
         3) executor:
         4) ProducerType: 单生产者还是多生产者
         5) waitStrategy: 等待策略
         */
        Disruptor<OrderEvent> disruptor =
            new Disruptor<>(orderEventFactory, ringBufferSize, threadFactory, ProducerType.SINGLE,
                new BlockingWaitStrategy());

        // 2. 添加消费者的监听
        disruptor.handleEventsWith(new OrderEventConsumer());

        // 3. 启动disruptor
        disruptor.start();

        // 4. 获取实际存储数据的容器: RingBuffer
        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        OrderEventProducer producer = new OrderEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);

        for (long i = 0; i < 5; i++) {
            bb.putLong(0, i);
            producer.sendData(bb);
        }

        disruptor.shutdown();
    }

}
