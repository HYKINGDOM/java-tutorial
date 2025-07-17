package com.java.tutorial.project.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

        String getenv = System.getenv("aliyunbailian-sdk1");
        getenv = getEnvApiString(getenv);

        return OpenAiChatModel.builder().apiKey(getenv).logRequests(true).logResponses(true).modelName("qwen-plus")
            .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1").build();
    }

    @Bean
    public ChatModel chatModelDeepSeek() {

        String getenv = System.getenv("deepseek");
        getenv = getEnvApiString(getenv);
        return OpenAiChatModel.builder().apiKey(getenv).logRequests(true).logResponses(true).modelName("deepseek-chat")
            //.modelName("deepseek-reasoner")
            .baseUrl("https://api.deepseek.com/v1").build();
    }

    private static String getEnvApiString(String getenv) {
        log.info("getenv:{}", getenv);
        if (!getenv.contains("sk-")) {
            getenv = "sk-" + getenv;
        }
        log.info("getenv end:{}", getenv);
        return getenv;
    }
}

