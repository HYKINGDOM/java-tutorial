package com.java.tutorial.project.config;

import com.java.tutorial.project.collector.TraceCollector;
import com.java.tutorial.project.domain.TraceContext;
import com.java.tutorial.project.domain.TraceEvent;
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

    @Override
    public void process(Record record) {
        // 提取上游轨迹信息
        TraceContext traceContext = extractTraceContext(context);

        // 记录处理开始
        TraceEvent startEvent =
            TraceEvent.builder().traceId(traceContext.getTraceId()).messageId(traceContext.getMessageId())
                .timestamp(System.currentTimeMillis()).phase(TracePhase.STREAMS_PROCESS_START)
                .applicationId(context.applicationId()).build();

        collector.collect(startEvent);

        try {
            // 业务处理
            String result = processValue(value);

            // 转发结果
            context.forward(key, result);

            // 记录处理完成
            recordSuccess(traceContext);
        } catch (Exception e) {
            // 记录处理失败
            recordError(traceContext, e);
            throw e;
        }
    }
}
