package com.java.tutorial.project.event.consumer;

import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.domain.OrderEvent;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * 消费者
 *
 * @author meta
 */
@Slf4j
public class OrderEventConsumer implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws InterruptedException {
        TraceIDUtil.setTraceIdToMdcAndTtl(event.getTractId());
        Thread.sleep(3000);
        log.info("OrderEventHandler onEvent event: {}, sequence:{}, endOfBatch:{}", event.getValue(), sequence,
            endOfBatch);
    }
}
