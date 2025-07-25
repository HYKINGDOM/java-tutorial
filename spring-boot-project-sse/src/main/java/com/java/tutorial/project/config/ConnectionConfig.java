package com.java.tutorial.project.config;

import com.java.tutorial.project.service.ConnectionInfoService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author meta
 */
@Component
@Slf4j
public class ConnectionConfig {

    @Resource
    private ConnectionInfoService connectionInfoService;

    @PostConstruct
    private void init() {
        log.info("清空项目重启前遗留的MongoDB中的连接信息");
        //connectionInfoService.deleteAll();
        log.info("清空完成");
    }
}
