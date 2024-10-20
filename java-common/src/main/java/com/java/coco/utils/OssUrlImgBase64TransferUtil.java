package com.java.coco.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * 图片下载转字节流
 *
 * @author meta
 */
@Slf4j
public class OssUrlImgBase64TransferUtil {

    public static final String REQUEST_METHOD = "GET";
    public static final int CONNECT_TIMEOUT = 5000;
    public static final String REGEX = "[\\s*\t\n\r]";
    public static final String REPLACEMENT = "";

    public static String getBase64FromImageUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(REQUEST_METHOD);
            conn.setConnectTimeout(CONNECT_TIMEOUT);

            try (InputStream in = conn.getInputStream(); ByteArrayOutputStream data = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) != -1) {
                    data.write(buffer, 0, length);
                }

                byte[] imageBytes = data.toByteArray();
                String base64Encoded = Base64.getEncoder().encodeToString(imageBytes);
                return base64Encoded.replaceAll(REGEX, REPLACEMENT);
            }
        } catch (IOException e) {
            log.error("Failed to convert image URL to Base64: {}", e.getLocalizedMessage());
        }
        return null;
    }
}
