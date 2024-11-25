package com.java.tutorial.project.event;

import com.alibaba.fastjson2.JSON;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.domain.DataEventRequest;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kscs
 */
@Slf4j
public class DataEventHandler implements EventHandler<DataEventRequest> {

    private final RingBuffer<DataEventRequest> ringBufferB;

    public DataEventHandler(RingBuffer<DataEventRequest> ringBufferB) {
        this.ringBufferB = ringBufferB;
    }

    private Integer goodsCount = 2;


    @Override
    public void onEvent(DataEventRequest event, long sequence, boolean endOfBatch) throws Exception {
        TraceIDUtil.setTraceIdToMdcAndTtl(event.getTraceId());
        // 处理数据
        log.info("Processing data: {}, thread name:{}", JSON.toJSONString(event), Thread.currentThread().getName());



        String msg;
        if (goodsCount != 0){
            goodsCount--;
            msg = "订单生成成功: " + event.getData();
        }else {
            msg = "订单生成失败: " + event.getData();
        }
        log.info(msg);
        Thread.sleep(5000);

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
        }
    }
}



