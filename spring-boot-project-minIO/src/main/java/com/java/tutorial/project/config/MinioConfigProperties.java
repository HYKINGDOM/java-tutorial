package com.java.tutorial.project.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author meta
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfigProperties {

    private String accessKey;

    private String secretKey;

    private String url;

    private String bucketName;
}
