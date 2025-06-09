package com.java.tutorial.project.event.consumer;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson2.JSON;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.config.DisruptorConfig;
import com.java.tutorial.project.domain.DataEventRequest;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
public class DataEventHandlerConsumer implements EventHandler<DataEventRequest> {

    /**
     * 队列 B
     *
     * @see DisruptorConfig#ringBufferB(Disruptor)
     */
    private final RingBuffer<DataEventRequest> ringBufferB;

    public DataEventHandlerConsumer(RingBuffer<DataEventRequest> ringBufferB) {
        this.ringBufferB = ringBufferB;
    }

    private Integer goodsCount = 2;

    @Override
    public void onEvent(DataEventRequest event, long sequence, boolean endOfBatch) {
        TraceIDUtil.setTraceIdToMdcAndTtl(event.getTraceId());
        // 处理数据
        log.info("Processing data: {}, thread name:{}", JSON.toJSONString(event), Thread.currentThread().getName());

        String msg;
        if (goodsCount != 0) {
            goodsCount--;
            msg = "订单生成成功: " + event.getData();
        } else {
            msg = "订单生成失败: " + event.getData();
        }
        log.info(msg);
        threadSleep();

        // 将处理后的数据放入队列 B
        long sequenceB = ringBufferB.next();
        try {
            DataEventRequest resultEvent = ringBufferB.get(sequenceB);
            resultEvent.setData(msg);
            resultEvent.setUserId(event.getUserId());
            resultEvent.setTraceId(event.getTraceId());
            resultEvent.setDeferredResult(event.getDeferredResult());
        } finally {
            ringBufferB.publish(sequenceB);
            log.info("DataEventHandlerConsumer: {}", msg);
        }
    }

    private static void threadSleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



