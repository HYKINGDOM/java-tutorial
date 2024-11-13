package com.java.tutorial.project.controller;

import com.java.tutorial.project.service.MinioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/files")
public class PreviewController {

    @Resource
    private MinioService minioService;

    @GetMapping("/preview/{fileName}")
    public ResponseEntity<String> previewFile(@PathVariable String fileName) {
        String previewUrl = minioService.getPreviewUrl(fileName);
        return ResponseEntity.ok(previewUrl);
    }
}
