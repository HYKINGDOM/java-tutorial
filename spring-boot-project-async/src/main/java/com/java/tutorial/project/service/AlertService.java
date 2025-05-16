package com.java.tutorial.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlertService {
    public void sendAlert(String finalMsg, String[] split) {
        log.info(finalMsg);
    }
}
