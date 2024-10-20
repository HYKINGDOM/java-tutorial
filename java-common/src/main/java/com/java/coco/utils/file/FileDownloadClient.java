package com.java.coco.utils.file;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

/**
 * @author meta
 */
@Slf4j
public class FileDownloadClient {

    private static final String JPG = ".jpg";

    /**
     * 下载并转码文件
     *
     * @param imageUrl
     * @return
     */
    public byte[] downloadImageAsBase64(String imageUrl) {
        try {
            // 下载图片到临时文件
            URL url = new URL(imageUrl);
            Path tempFile = Files.createTempFile(String.valueOf(System.currentTimeMillis()), JPG);
            try (InputStream inputStream = url.openStream()) {
                Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // 将图片文件转换为Base64编码的字节数组
            byte[] imageBytes = Files.readAllBytes(tempFile);
            byte[] base64Bytes = Base64.getEncoder().encode(imageBytes);

            // 删除临时文件
            Files.deleteIfExists(tempFile);

            // 返回Base64编码的字节数组
            return base64Bytes;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    public byte[] downloadImageAsBase64V2(String imageUrl) {
        try {
            // 下载图片到临时文件
            URL url = new URL(imageUrl);
            Path tempFile = Files.createTempFile(String.valueOf(System.currentTimeMillis()), JPG);

            InputStream inputStream = url.openStream();
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
            // 将图片文件转换为Base64编码的字节数组
            byte[] imageBytes = Files.readAllBytes(tempFile);
            // 删除临时文件
            Files.deleteIfExists(tempFile);
            // 返回Base64编码的字节数组
            return imageBytes;
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
