package com.java.tutorial.project.config;



import org.apache.logging.log4j.message.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class SinkConfig {

    /**
     * 配置可重放最后1条消息的多订阅者Sink
     */
    @Bean
    public Sinks.Many<Message> messageSink() {
        // replay().limit(1)：新订阅者会收到最后1条历史消息
        return Sinks.many().replay().limit(1);
    }
}
