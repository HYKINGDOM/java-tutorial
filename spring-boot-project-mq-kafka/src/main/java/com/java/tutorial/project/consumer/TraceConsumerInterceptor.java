package com.java.tutorial.project.consumer;

import com.java.tutorial.project.collector.TraceCollector;
import com.java.tutorial.project.constant.TracePhase;
import com.java.tutorial.project.domain.TraceEvent;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

import static com.java.tutorial.project.util.KafkaUtils.extractTraceId;
import static com.java.tutorial.project.util.KafkaUtils.getConsumerGroup;

/**
 * @author meta
 */
@Component
public class TraceConsumerInterceptor implements ConsumerInterceptor<String, String> {

    @Resource
    private TraceCollector collector;

    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        // 记录消费开始轨迹
        for (ConsumerRecord<String, String> record : records) {
            // 从消息头中提取 traceId
            String traceId = extractTraceId(record);

            TraceEvent event = TraceEvent.builder()
                .traceId(traceId)
                .messageId(extractMessageId(record))
                .timestamp(System.currentTimeMillis())
                .phase(TracePhase.CONSUMER_RECEIVE)
                .topic(record.topic())
                .partition(record.partition())
                .offset(record.offset())
                .consumerGroup(getConsumerGroup())
                .consumerId(getConsumerId())
                .build();

            collector.collect(event);
        }

        return records;
    }

    private String extractMessageId(ConsumerRecord<String, String> record) {
        return null;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        // 记录位移提交轨迹
        for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()) {
            TraceEvent event = TraceEvent.builder()
                .timestamp(System.currentTimeMillis())
                .phase(TracePhase.CONSUMER_COMMIT)
                .topic(entry.getKey().topic())
                .partition(entry.getKey().partition())
                .offset(entry.getValue().offset())
                .consumerGroup(getConsumerGroup())
                .consumerId(getConsumerId())
                .build();

            collector.collect(event);
        }
    }

    private long getConsumerId() {
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
