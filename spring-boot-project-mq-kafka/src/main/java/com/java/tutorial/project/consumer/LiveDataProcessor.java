package com.java.tutorial.project.consumer;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@Configuration
@EnableKafkaStreams
public class LiveDataProcessor {

    private static final String INPUT_TOPIC = "live-data-topic";
    private static final String OUTPUT_TOPIC = "processed-live-data-topic";

    @Autowired
    private DatabaseWriter databaseWriter;

    @Bean
    public KStream<String, String> kStream(StreamsBuilder builder) {
        // 从输入主题读取数据
        KStream<String, String> sourceStream =
            builder.stream(INPUT_TOPIC, Consumed.with(Serdes.String(), Serdes.String()));

        // 数据处理逻辑
        KStream<String, String> processedStream = sourceStream.filter((key, value) -> {
            // 过滤掉无效数据（例如观众数为 0 的直播）
            int viewers = Integer.parseInt(value.split("\"viewers\":")[1].split(",")[0].trim());
            return viewers > 0;
        }).mapValues(value -> {
            // 对数据进行处理（例如增加一个字段）
            String processedValue = value.replace("}", ", \"processed\": true}");
            databaseWriter.saveToDatabase(processedValue); // 写入数据库
            return processedValue;
        });

        // 将处理后的数据写入输出主题
        processedStream.to(OUTPUT_TOPIC, Produced.with(Serdes.String(), Serdes.String()));

        return processedStream;
    }
}

