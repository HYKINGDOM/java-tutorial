package com.java.tutorial.project.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.java.tutorial.project.util.ApiKeyEnvUtil.getEnvApiString;

/**
 * 语言模型配置
 *
 * @author meta
 */
@Slf4j
@Configuration
public class LLMStreamConfig {
    @Bean
    public StreamingChatModel chatStreamingModelQwen() {

        String getenv = System.getenv("aliyunbailian-sdk1");
        getenv = getEnvApiString(getenv);

        return OpenAiStreamingChatModel.builder().logRequests(true).logResponses(true).apiKey(getenv)
            .modelName("qwen-plus").baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1").build();
    }

    @Bean
    public StreamingChatModel chatStreamingModelDeepSeek() {

        String getenv = System.getenv("deepseek");
        getenv = getEnvApiString(getenv);
        return OpenAiStreamingChatModel.builder().logRequests(true).logResponses(true).apiKey(getenv)
                .listeners(List.of(new TestChatModelListener()))
            .modelName("deepseek-chat")
            //.modelName("deepseek-reasoner")
            .baseUrl("https://api.deepseek.com/v1").build();
    }

}

