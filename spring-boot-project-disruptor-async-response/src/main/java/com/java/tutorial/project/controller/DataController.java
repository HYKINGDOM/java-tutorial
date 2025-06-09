package com.java.tutorial.project.controller;

import com.java.coco.utils.TraceIDUtil;
import com.java.tutorial.project.config.DisruptorConfig;
import com.java.tutorial.project.domain.DataEventRequest;
import com.java.tutorial.project.domain.DataEventResponse;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * 数据异步处理控制器
 * @author meta
 */
@RestController
public class DataController {

    /**
     * 需要再配置文件中定义
     * @see DisruptorConfig#producerData(Disruptor)
     */
    private final RingBuffer<DataEventRequest> producerData;

    public DataController(RingBuffer<DataEventRequest> producerData) {
        this.producerData = producerData;
    }

    /**
     * 提交数据事件请求的处理方法
     * 该方法使用非阻塞方式处理请求，通过环形缓冲区（Disruptor模式）异步处理数据事件
     *
     * @param dataEventRequest 数据事件请求对象，包含需要处理的数据和用户信息
     * @return DeferredResult封装了处理结果，用于异步返回处理结果
     */
    @PostMapping("/submit")
    public DeferredResult<DataEventResponse> submitData(@RequestBody DataEventRequest dataEventRequest) {
        // 创建一个DeferredResult对象，用于异步返回处理结果
        DeferredResult<DataEventResponse> deferredResult = new DeferredResult<>();
        // 将DeferredResult对象设置到请求对象中，以便在处理完成后能够通知请求方
        dataEventRequest.setDeferredResult(deferredResult);
        // 从环形缓冲区中获取下一个序列号，用于存放数据事件请求
        long sequence = producerData.next();
        try {
            // 获取序列号对应的事件对象
            DataEventRequest event = producerData.get(sequence);
            // 将请求对象中的数据复制到事件对象中
            event.setData(dataEventRequest.getData());
            event.setUserId(dataEventRequest.getUserId());
            // 设置跟踪ID，用于追踪请求流程
            event.setTraceId(TraceIDUtil.getTraceId());
            // 将DeferredResult对象设置到事件对象中，以便在事件处理完成后能够通知请求方
            event.setDeferredResult(deferredResult);
        } finally {
            // 发布事件，表示事件已经准备好，可以被消费者线程处理
            producerData.publish(sequence);
        }
        // 返回DeferredResult对象，等待事件处理完成后的异步结果
        return deferredResult;
    }

}

