package com.java.tutorial.project.client;

import com.alibaba.fastjson.JSON;
import com.java.coco.utils.http.HttpClientUtil;
import java.util.HashMap;
import java.util.Map;

import com.java.coco.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MsgClient {
    @Value("${sse.msg.send.group.url:http://10.0.220.31:8080/msg/group}")
    private String groupUrl;

    //    @Value("${product.msg.send.person.url:http://10.0.220.31:8080/msg/person}")
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
        String response = HttpUtils.doPostWithBody(groupUrl, JSON.toJSONString(params));
        log.info("response = {}", response);
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
        String response = HttpUtils.doPostWithBody(personUrl, JSON.toJSONString(params));
        log.info("response = {}", response);
    }
}
