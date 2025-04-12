package com.java.tutorial.project.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

public class FileHeaderValidator {


    public static String getRealFileType(MultipartFile multipartFile) {
        try {
            String magicNumber = bytesToHex(Arrays.copyOfRange(multipartFile.getBytes(), 0, 4));
            switch (magicNumber) {
                case "FFD8FFE0":
                    return "image/jpeg";
                case "89504E47":
                    return "image/png";
                //...其他类型
                default:
                    return "unknown";
            }
        } catch (IOException e) {
            return "unknown";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
