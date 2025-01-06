package com.java.tutorial.project.util;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @author meta
 */
public class KafkaUtils {

    public static final String TRACE_ID = "TRACE_ID";
    public static final String MESSAGE_ID = "MESSAGE_ID";
    public static final String TIMESTAMP = "TRACE_TIMESTAMP";
    public static final String SOURCE = "TRACE_SOURCE";

    public static final String TRACE_ID_HEADER = "traceId";

    public static String extractTraceId(ConsumerRecord<String, String> record) {
        // 从消息头中提取 traceId
        if (record.headers().lastHeader(TRACE_ID_HEADER) != null) {
            return new String(record.headers().lastHeader(TRACE_ID_HEADER).value());
        }
        return null;
    }

    public static String getConsumerGroup() {
        return null;
    }

    /**
     * 生成消息ID
     *
     * @return
     */
    public static String generateMessageId() {

        return String.valueOf(System.currentTimeMillis());
    }

}
