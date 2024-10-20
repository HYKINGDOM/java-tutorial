package com.java.tutorial.project.config.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author meta
 */
@Component
@Slf4j
public class MsgClient {

    @Value("${sse.msg.send.group.url:http://10.0.220.31:8080/msg/group}")
    private String groupUrl;

    private String personUrl = "http://127.0.0.1:8080/msg/person";

    @Value("${sse.msg.send.group.id:35}")
    private Long groupId;

    public void groupSend(String title, String content) {
        Map<String, Object> params = new HashMap<>();
        params.put("groupId", groupId);
        params.put("title", title);
        params.put("content", content);
        params.put("msgType", 2);
        params.put("templateId", 5);
        params.put("appCode", 8);
        params.put("linkUrl", "");
        params.put("schedule", "");

    }

    public void personSend(String title, String content, Long userId) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("title", title);
        params.put("content", content);
        params.put("msgType", 2);
        params.put("templateId", 5);
        params.put("appCode", 8);
        params.put("linkUrl", "");
        params.put("schedule", "");

    }
}
