package com.java.tutorial.project.producer;

import com.java.tutorial.project.collector.TraceCollector;
import com.java.tutorial.project.constant.TracePhase;
import com.java.tutorial.project.domain.TraceEvent;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

import static com.java.tutorial.project.util.KafkaUtils.generateMessageId;

/**
 * @author meta
 */
@Component
public class TraceProducerInterceptor implements ProducerInterceptor<String, String> {

    @Resource
    private TraceCollector collector;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 在发送消息前记录轨迹
        String traceId = record.headers().lastHeader("TRACE_ID").value().toString();

        // 构建轨迹事件
        TraceEvent event =
            TraceEvent.builder().traceId(traceId).messageId(generateMessageId()).timestamp(System.currentTimeMillis())
                .phase(TracePhase.PRODUCER_SEND).topic(record.topic()).partition(record.partition()).build();

        // 收集轨迹
        collector.collect(event);

        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

        // 记录发送结果 - 从元数据中提取TraceId
        String traceId = extractRecordMetadataTraceId(metadata);

        TraceEvent event = TraceEvent.builder().traceId(traceId).messageId(extractMessageId(metadata))
            .timestamp(System.currentTimeMillis()).phase(TracePhase.PRODUCER_ACK).topic(metadata.topic())
            .partition(metadata.partition()).offset(metadata.offset()).status(exception == null ? "SUCCESS" : "FAILED")
            .errorMessage(exception != null ? exception.getMessage() : null).build();

        collector.collect(event);
    }

    private String extractRecordMetadataTraceId(RecordMetadata metadata) {
        return null;
    }

    private String extractMessageId(RecordMetadata metadata) {
        return null;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
