package com.java.tutorial.project.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Configuration
public class MinioConfiguration {

    @Resource
    private MinioConfigProperties minioConfigProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(minioConfigProperties.getUrl())
            .credentials(minioConfigProperties.getAccessKey(), minioConfigProperties.getSecretKey()).build();
    }
}
