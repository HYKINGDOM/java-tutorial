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
        log.info("getenv:{}", getenv);
        if (!getenv.contains("sk-")) {
            getenv = "sk-" + getenv;
        }
        log.info("getenv end:{}", getenv);

        return OpenAiChatModel.builder()
                .apiKey(getenv)
                .modelName("qwen-plus")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

}

