package com.java.tutorial.project.util;

import cn.hutool.http.HttpStatus;
import com.java.tutorial.project.config.MinioConfigProperties;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PostPolicy;
import io.minio.PutObjectArgs;
import io.minio.RemoveBucketArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Minio工具类
 */
@Slf4j
@Component
public class MinioUtils {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioConfigProperties minioProperties;

    /**
     * @param name 名字
     * @return boolean 判断bucket是否存在，不存在则创建
     */
    public boolean existBucket(String name) {
        boolean exists;
        try {
            exists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(name).build());
            if (!exists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(name).build());
                exists = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            exists = false;
        }
        return exists;
    }

    /**
     * @param bucketName 存储bucket名称
     * @return {@link Boolean } 创建存储bucket
     */
    public Boolean makeBucket(String bucketName) {
        try {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param bucketName 存储bucket名称
     * @return {@link Boolean } 删除存储bucket
     */
    public Boolean removeBucket(String bucketName) {
        try {
            minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * @param fileName 文件名称
     * @param time     时间
     * @return {@link Map } 获取上传临时签名
     */
    public Map<String, String> getPolicy(String fileName, ZonedDateTime time) {
        PostPolicy postPolicy = new PostPolicy(minioProperties.getBucketName(), time);
        postPolicy.addEqualsCondition("key", fileName);

        try {
            Map<String, String> map = minioClient.getPresignedPostFormData(postPolicy);
            Map<String, String> resultMap = new HashMap<>();
            map.forEach((k, v) -> {
                if (k.contains("-")) {
                    resultMap.put(k.replace("-", ""), v);
                } else {
                    resultMap.put(k, v);
                }
            });
            resultMap.put("host", minioProperties.getUrl() + "/" + minioProperties.getBucketName());
            return resultMap;
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
            InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
            XmlParserException e) {
            log.error("Failed to get a temporary upload signature: {}", fileName, e);
        }
        return null;
    }

    /**
     * @param objectName 对象名称
     * @param method     方法
     * @param time       时间
     * @param timeUnit   时间单位
     * @return {@link String } 获取上传文件的url
     */
    public String getPolicyUrl(String objectName, Method method, int time, TimeUnit timeUnit) {
        try {
            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().method(method).bucket(minioProperties.getBucketName())
                    .object(objectName).expiry(time, timeUnit).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
            InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
            XmlParserException e) {
            log.error("Failed to get file url: {}", objectName, e);
        }
        return null;
    }

    /**
     * @param file     文件
     * @param fileName 文件名称 上传文件
     */
    public void upload(MultipartFile file, String fileName) {
        if (file == null || fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("File and file name must not be null or empty");
        }

        // 使用putObject上传一个文件到存储桶中。
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs.builder().bucket(minioProperties.getBucketName()).object(fileName)
                .stream(inputStream, file.getSize(), -1).contentType(file.getContentType()).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
            InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
            XmlParserException e) {
            log.error("Failed to upload file: {}", fileName, e);
        }
    }

    /**
     * @param objectName 对象名称
     * @param time       时间
     * @param timeUnit   时间单位
     * @return {@link String } 根据filename获取文件访问地址
     */
    public String getUrl(String objectName, int time, TimeUnit timeUnit) {
        String url = null;
        try {
            url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(minioProperties.getBucketName())
                    .object(objectName).expiry(time, timeUnit).build());
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
            InvalidResponseException | IOException | NoSuchAlgorithmException | XmlParserException |
            ServerException e) {
            log.error("Failed to get presigned URL for object: {}", objectName, e);
        }
        return url;
    }

    /**
     * 下载指定文件
     *
     * @param fileName 文件名
     * @return {@link ResponseEntity }<{@link byte[] }> 包含文件内容的响应实体
     *
     *     此方法从MinIO对象存储中下载指定的文件，并返回包含文件内容的响应实体 它首先从MinIO服务器上获取对象（文件），将其内容复制到内存输出流中，
     *     然后根据文件名和内容长度构建HTTP响应头，最后返回包含文件内容和响应头的响应实体 如果在处理过程中遇到异常，它会记录错误并返回一个指示内部错误的响应实体
     */
    public ResponseEntity<byte[]> download(String fileName) {
        try (InputStream in = minioClient.getObject(
            GetObjectArgs.builder().bucket(minioProperties.getBucketName()).object(fileName).build());
            ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            IOUtils.copy(in, out);

            // 封装返回值
            byte[] bytes = out.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            headers.setContentLength(bytes.length);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccessControlExposeHeaders(List.of("*"));

            return new ResponseEntity<>(bytes, headers, HttpStatus.HTTP_OK);
        } catch (Exception e) {
            // 更详细的异常处理
            log.error("Error downloading file: {}", fileName, e);
            return ResponseEntity.status(HttpStatus.HTTP_INTERNAL_ERROR).build();
        }
    }

    /**
     * @param objectFile 对象文件
     * @return {@link String } 根据文件名和桶获取文件路径
     */
    public String getFileUrl(String objectFile) {
        try {

            return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(minioProperties.getBucketName())
                    .object(objectFile).build());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}


