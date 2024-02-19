package com.java.tutorial.project.controller;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CacheDemoController {

    private final Cache<String, Object> caffeineCache;

    @GetMapping("/test/{token}")
    public String testDemoCache(@PathVariable String token) {
        // 2. 存储数据
        caffeineCache.put("access_token", token);

        long estimatedSize = caffeineCache.estimatedSize();
        System.out.println(estimatedSize);

        // 3. 读取数据
        return (String)caffeineCache.asMap().get("access_token");
    }
}
