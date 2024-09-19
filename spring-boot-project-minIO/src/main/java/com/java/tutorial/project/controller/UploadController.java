package com.java.tutorial.project.controller;

import com.java.coco.common.Result;
import com.java.tutorial.project.service.MinioService;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
public class UploadController {

    @Resource
    private MinioService minioService;

    @PostMapping("/uploadMultiple")
    public Result<List<String>> uploadMultiple(@RequestParam("files") List<MultipartFile> files, @RequestParam("fileNames") List<String> fileNames) {
        List<String> urls = new ArrayList<>();
        for (String fileName : fileNames) {
            minioService.upload(files.get(fileNames.indexOf(fileName)), fileName);
            String url = minioService.getUrl(fileName, 7, TimeUnit.DAYS);
            urls.add(url);
        }
        return Result.success(urls);
    }

    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName) {
        minioService.upload(file, fileName);
        String url = minioService.getUrl(fileName, 7, TimeUnit.DAYS);
        return Result.success(url);
    }

    @GetMapping("/policy")
    public Result<Map<String, String>> policy(@RequestParam("fileName") String fileName) {
        Map<String, String> policy = minioService.getPolicy(fileName, ZonedDateTime.now().plusMinutes(10));
        return Result.success(policy);
    }

    @GetMapping("/uploadUrl")
    public Result uploadUrl(@RequestParam("fileName") String fileName) {
        String url = minioService.getPolicyUrl(fileName, Method.PUT, 2, TimeUnit.MINUTES);
        return Result.success(url);
    }

    @GetMapping("/url")
    public Result getUrl(@RequestParam("fileName") String fileName) {
        String url = minioService.getUrl(fileName, 7, TimeUnit.DAYS);
        return Result.success(url);
    }

}
