package com.java.tutorial.project.domain;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;

import static com.java.tutorial.project.util.KafkaUtils.MESSAGE_ID;
import static com.java.tutorial.project.util.KafkaUtils.SOURCE;
import static com.java.tutorial.project.util.KafkaUtils.TIMESTAMP;
import static com.java.tutorial.project.util.KafkaUtils.TRACE_ID;
import static com.java.tutorial.project.util.KafkaUtils.generateMessageId;

/**
 * @author meta
 */
public class TraceHeadersExtension {

    public static void inject(ProducerRecord<?, ?> record, String traceId) {
        record.headers().add(TRACE_ID, traceId.getBytes()).add(MESSAGE_ID, generateMessageId().getBytes())
            .add(TIMESTAMP, String.valueOf(System.currentTimeMillis()).getBytes())
            .add(SOURCE, getServiceName().getBytes());
    }

    private static String getServiceName() {
        return "project-service";
    }

    public static TraceContext extract(ConsumerRecord<?, ?> record) {
        String traceId = new String(record.headers().lastHeader(TRACE_ID).value());
        String messageId = new String(record.headers().lastHeader(MESSAGE_ID).value());
        long timestamp = Long.parseLong(new String(record.headers().lastHeader(TIMESTAMP).value()));
        String source = new String(record.headers().lastHeader(SOURCE).value());

        return new TraceContext(traceId, messageId, timestamp, source);
    }
}
