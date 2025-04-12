package com.java.tutorial.project.util;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author meta
 */
public class TikaDetector {

    /**
     * 检测实现
     *
     * @param file
     * @return
     */
    public static String detectMimeType(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Tika tika = new Tika();
            return tika.detect(is);
        } catch (IOException e) {
            return "unknown";
        }
    }
}
