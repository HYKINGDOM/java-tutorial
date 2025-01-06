package com.java.tutorial.project.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author meta
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * 消费监听
     *
     * @param record
     */
    @KafkaListener(topics = {"event-topic-first"})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        log.info("简单消费：{}-{}-{}", record.topic(), record.partition(), record.value());
    }

}
