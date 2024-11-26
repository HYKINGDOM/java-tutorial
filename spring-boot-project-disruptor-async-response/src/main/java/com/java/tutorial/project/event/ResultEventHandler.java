package com.java.tutorial.project.event;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.domain.DataEventRequest;
import com.java.tutorial.project.domain.DataEventResponse;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author meta
 */
@Slf4j
public class ResultEventHandler implements EventHandler<DataEventRequest> {
    @Override
    public void onEvent(DataEventRequest event, long sequence, boolean endOfBatch) {
        TraceIDUtil.setTraceIdToMdcAndTtl(event.getTraceId());
        // 返回结果给用户
        DataEventResponse dataEventResponse = new DataEventResponse();
        dataEventResponse.setData(event.getData());
        dataEventResponse.setUserId(event.getUserId());
        dataEventResponse.setThreadName(Thread.currentThread().getName());
        dataEventResponse.setNumber(RandomUtil.randomString(15));
        dataEventResponse.setTraceId(event.getTraceId());
        log.info("ResultEventHandler处理结果：{}, thread name:{}", JSON.toJSONString(dataEventResponse), Thread.currentThread().getName());
        event.getDeferredResult().setResult(dataEventResponse);
    }
}
