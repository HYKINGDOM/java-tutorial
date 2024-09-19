package com.java.tutorial.project.service;

import com.java.tutorial.project.config.MinioConfigProperties;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author meta
 */
@Slf4j
@Component
public class MinioService {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfigProperties minioConfigProperties;

    /**
     * @description: 获取上传临时签名
     */
    public Map<String, String> getPolicy(String fileName, ZonedDateTime time) {
        PostPolicy postPolicy = new PostPolicy(minioConfigProperties.getBucketName(), time);
        postPolicy.addEqualsCondition("key", fileName);
        try {
            Map<String, String> map = minioClient.getPresignedPostFormData(postPolicy);
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k, v) -> {
                resultMap.put(k.replaceAll("-", ""), v);
            });
            resultMap.put("host", minioConfigProperties.getUrl() + "/" + minioConfigProperties.getBucketName());
            return resultMap;
        } catch (Exception e) {
            log.error("获取上传临时签名失败", e);
        }
        return null;
    }

    /**
     * @description: 获取上传文件的url
     * @dateTime: 2021/5/13 14:15
     */
    public String getPolicyUrl(String objectName, Method method, int time, TimeUnit timeUnit) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().method(method).bucket(minioConfigProperties.getBucketName())
                    .object(objectName).expiry(time, timeUnit).build());
        } catch (Exception e) {
            log.error("获取上传文件的url失败", e);
        }
        return null;
    }

    /**
     * @description: 上传文件
     */
    public void upload(MultipartFile file, String fileName) {
        // 使用putObject上传一个文件到存储桶中。
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder().bucket(minioConfigProperties.getBucketName()).object(fileName)
                .stream(inputStream, file.getSize(), -1).contentType(file.getContentType()).build());
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
    }

    public String getUrl(String objectName, int time, TimeUnit timeUnit) {
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(minioConfigProperties.getBucketName())
                    .object(objectName).expiry(time, timeUnit).build());
        } catch (Exception e) {
            log.error("上传文件失败", e);
        }
        return url;
    }

}
