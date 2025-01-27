package com.java.tutorial.project.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;

@Component
public class LiveDataProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "live-data-topic";

    // 每 3 秒发送一次直播数据
    @Scheduled(fixedRate = 3000)
    public void sendLiveData() {
        String liveData = generateLiveData();
        kafkaTemplate.send(TOPIC, liveData);
        System.out.println("Sent data: " + liveData);
    }

    // 生成模拟直播数据
    private String generateLiveData() {
        Random random = new Random();
        // 直播 ID
        int liveId = random.nextInt(1000);
        // 观众数
        int viewers = random.nextInt(10000);
        // 点赞数
        int likes = random.nextInt(1000);
        return String.format("{\"liveId\": %d, \"viewers\": %d, \"likes\": %d}", liveId, viewers, likes);
    }
}

