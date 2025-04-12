package com.java.tutorial.project.controller;

import cn.hutool.core.lang.UUID;
import com.java.tutorial.project.util.FileHeaderValidator;
import com.java.tutorial.project.util.ImageValidator;
import com.java.tutorial.project.util.TikaDetector;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * 文件上传控制器
 *
 * @author meta
 */
@RestController
@RequestMapping("/file")
public class SecureUploadController {

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("expectedType") String expectedType) {

        // 第一层验证
        String realType = FileHeaderValidator.getRealFileType(file);
        if (!expectedType.equals(realType)) {
            throw new RuntimeException("文件头类型不匹配");
        }

        // 第二层验证
        String mimeType = TikaDetector.detectMimeType(file);
        if (!expectedType.equals(mimeType)) {
            throw new RuntimeException("MIME类型不匹配");
        }

        // 第三层验证（示例为图片）
        if ("image".equals(expectedType.split("/")[0])) {
            if (!ImageValidator.isRealImage(file)) {
                throw new RuntimeException("非有效图片文件");
            }
        }

        // 安全存储
        String newFileName = UUID.randomUUID() + "." + realType.split("/")[1];
        Path path = Paths.get("/secure-upload", newFileName);

        try {
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("文件写入失败");
        }


        return ResponseEntity.ok("上传成功");
    }


}
