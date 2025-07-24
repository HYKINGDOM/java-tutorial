package com.java.tutorial.project.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

public interface WorkOrderAssistant {

    @SystemMessage("你是一个工单处理助手，当用户需要生成工单编号时，请使用工具生成工单编号")
    String generateWorkOrderCode(@UserMessage String userMessage);
}