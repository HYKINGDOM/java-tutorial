package com.java.tutorial.project.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Slf4j
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(

            success -> {
                // 消息发送到的topic
                String topic = "topic1";
                // 消息发送到的分区
                int partition = 1;
                // 消息在分区内的offset
                long offset = 1;
                log.info("发送消息成功:{}-{}-{}", topic, partition, offset);
            }, failure -> {
                log.error("发送消息失败:{}", failure.getMessage());
            });
    }

    public void sendFutureCallbackMessage(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(@NonNull Throwable throwable) {
                log.error("发送消息失败：{}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("发送消息成功：{}-{}-{}", result.getRecordMetadata().topic(),
                    result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            }
        });
    }
}
