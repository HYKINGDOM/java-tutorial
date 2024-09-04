package com.java.tutorial.project.config;

import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class OrderEventProducer {

    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducer(RingBuffer<OrderEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void sendData(ByteBuffer data) {
        // 1、在生产者发送消息的时候, 首先需要从我们的ringBuffer里面获取一个可用的序号
        long sequence = ringBuffer.next();
        try {
            //2、注意此时获取的OrderEvent对象是一个没有被赋值的空对象
            OrderEvent event = ringBuffer.get(sequence);
            //3、进行实际的赋值处理
            event.setValue(data.getLong(0));
        } finally {
            //4、 提交发布操作
            ringBuffer.publish(sequence);
        }
    }
}
