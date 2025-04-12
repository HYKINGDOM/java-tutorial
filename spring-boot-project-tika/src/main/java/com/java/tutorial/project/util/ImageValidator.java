package com.java.tutorial.project.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片文件校验器
 *
 * @author meta
 */
public class ImageValidator {

    public static boolean isRealImage(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Image image = ImageIO.read(is);
            return image != null;
        } catch (IOException e) {
            return false;
        }
    }
}
