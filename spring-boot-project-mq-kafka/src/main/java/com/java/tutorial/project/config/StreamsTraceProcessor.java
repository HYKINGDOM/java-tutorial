package com.java.tutorial.project.config;

import com.java.tutorial.project.collector.TraceCollector;
import com.java.tutorial.project.domain.TraceContext;
import com.java.tutorial.project.domain.TraceEvent;
import com.java.tutorial.project.constant.TracePhase;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.Record;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kscs
 */
@Component
public class StreamsTraceProcessor implements Processor<String, String, String, String> {

    @Resource
    private TraceCollector collector;

    @Resource
    private ProcessorContext context;

    private void recordSuccess(TraceContext traceContext) {
        TraceEvent event =
            TraceEvent.builder().traceId(traceContext.getTraceId()).messageId(traceContext.getMessageId())
                .timestamp(System.currentTimeMillis()).phase(TracePhase.STREAMS_PROCESS_END).status("SUCCESS").build();

        collector.collect(event);
    }

    private void recordError(TraceContext traceContext, Exception e) {
        TraceEvent event =
            TraceEvent.builder().traceId(traceContext.getTraceId()).messageId(traceContext.getMessageId())
                .timestamp(System.currentTimeMillis()).phase(TracePhase.STREAMS_PROCESS_END).status("FAILED")
                .errorMessage(e.getMessage()).build();

        collector.collect(event);
    }
    
    private TraceContext extractTraceContext(ProcessorContext context) {
        // 这里应该从ProcessorContext中提取TraceContext信息
        // 由于缺少具体实现细节，这里提供一个示例实现
        return new TraceContext("default-trace-id", "default-message-id", System.currentTimeMillis(), "kafka-streams");
    }
    
    private String processValue(String value) {
        // 这里应该实现具体的业务逻辑处理
        // 由于缺少具体需求，这里简单返回原值
        return value;
    }

    @Override
    public void process(Record<String, String> record) {
        // 提取上游轨迹信息
        TraceContext traceContext = extractTraceContext(context);

        // 记录处理开始
        TraceEvent startEvent =
            TraceEvent.builder().traceId(traceContext.getTraceId()).messageId(traceContext.getMessageId())
                .timestamp(System.currentTimeMillis()).phase(TracePhase.STREAMS_PROCESS_START).build();

        collector.collect(startEvent);

        try {
            // 业务处理
            String result = processValue(record.value());

            // 转发结果
            context.forward(record.key(), result);

            // 记录处理完成
            recordSuccess(traceContext);
        } catch (Exception e) {
            // 记录处理失败
            recordError(traceContext, e);
            throw e;
        }
    }
}
