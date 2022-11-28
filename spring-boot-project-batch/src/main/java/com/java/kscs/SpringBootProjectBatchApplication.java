package com.java.kscs;

import com.java.coco.utils.TraceIDUtil;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBootProjectBatchApplication {

    public static void main(String[] args) {
        TraceIDUtil.getTraceId();
        SpringApplication.run(SpringBootProjectBatchApplication.class, args);
    }

}
