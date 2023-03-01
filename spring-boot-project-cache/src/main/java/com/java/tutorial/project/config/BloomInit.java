package com.java.tutorial.project.config;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: BloomInit
 * @description: 初始化布隆过滤器
 * @date: 2022/07/11
 * @author: Sora33
 */
@Log4j2
@Configuration
public class BloomInit implements CommandLineRunner {


    @Override
    public void run(String... args) throws Exception {
        this.bloomInit();
    }

    /**
     * 初始化布隆过滤器
     */
    @Bean
    public BloomFilter bloomInit() {
        // 初始化布隆过滤器，设置数据类型，数组长度和误差值
        BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000L, 0.01);
        log.info("布隆过滤器装载完成");
        return bloomFilter;
    }
}

