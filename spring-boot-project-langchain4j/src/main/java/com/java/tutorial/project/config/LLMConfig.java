package com.java.tutorial.project.config;

import cn.hutool.extra.spring.SpringUtil;
import com.java.tutorial.project.service.FunctionAssistant;
import com.java.tutorial.project.tool.WorkOrderTool;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.java.tutorial.project.util.ApiKeyEnvUtil.getEnvApiKeyByEnvName;
import static com.java.tutorial.project.util.ApiKeyEnvUtil.getEnvApiString;

/**
 * 语言模型配置
 *
 * @author meta
 */
@Slf4j
@Configuration
public class LLMConfig {
    @Bean
    public ChatModel chatModelQwen() {

        String apiKeyByEnvName = getEnvApiKeyByEnvName("aliyunbailian-sdk1");

        return OpenAiChatModel.builder().apiKey(apiKeyByEnvName).logRequests(true).logResponses(true).modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1").build();
    }

    @Bean
    public ChatModel chatModelDeepSeek() {

        String getenv = System.getenv("deepseek");
        getenv = getEnvApiString(getenv);
        return OpenAiChatModel.builder().apiKey(getenv).logRequests(true).logResponses(true)
                // 重试机制共计2次
                .maxRetries(2)
                .modelName("deepseek-chat")
                //.modelName("deepseek-reasoner")
                .baseUrl("https://api.deepseek.com/v1").build();
    }


    /**
     * @param chatModelQwen
     * @return
     */
    @Bean
    public FunctionAssistant functionAssistant(@Qualifier("chatModelQwen") ChatModel chatModelQwen) {
        return AiServices.builder(FunctionAssistant.class)
                .chatModel(chatModelQwen)
                .tools(SpringUtil.getBean(WorkOrderTool.class))
                .build();
    }


    @Bean
    public ChatModel chatModelLocalDeepSeek() {

        String getenv = System.getenv("deepseek");
        getenv = getEnvApiString(getenv);
        return OpenAiChatModel.builder().apiKey(getenv).logRequests(true).logResponses(true)
                // 重试机制共计2次
                .maxRetries(2)
                .modelName("huihui_ai/deepseek-r1-abliterated:14b")
                //.modelName("deepseek-reasoner")
                .baseUrl("http://192.168.5.4:11434/api/chat").build();
    }
}

