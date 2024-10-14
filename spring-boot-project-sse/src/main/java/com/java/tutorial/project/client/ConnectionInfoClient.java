package com.java.tutorial.project.client;

import com.java.tutorial.project.service.ConnectionInfoService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author meta
 */
@Component
@Slf4j
public class ConnectionInfoClient {
    @Resource
    private ConnectionInfoService connectionInfoService;

    @PostConstruct
    private void init() {
        log.info("清空项目重启前遗留的MongoDB中的连接信息");
        connectionInfoService.deleteAll();
        log.info("清空完成");
    }
}
