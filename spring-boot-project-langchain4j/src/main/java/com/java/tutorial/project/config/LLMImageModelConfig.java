package com.java.tutorial.project.config;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.java.tutorial.project.util.ApiKeyEnvUtil.getEnvApiKeyByEnvName;

/**
 * LLMImageModelConfig
 *
 * @author meta
 */
@Configuration
public class LLMImageModelConfig {


    @Bean
    public ChatModel imageModel() {

        String envApiKey = getEnvApiKeyByEnvName("aliyunbailian-sdk1");

        return OpenAiChatModel.builder()
                .apiKey(envApiKey)
                //qwen-vl-max 是一个多模态大模型，支持图片和文本的结合输入，适用于视觉-语言任务。
                .modelName("qwen-vl-max")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }


//    /**
//     * 测试通义万象来实现图片生成，
//     * 官网说明：https://help.aliyun.com/zh/model-studio/text-to-image
//     */
//    @Bean
//    public WanxImageModel wanxImageModel() {
//        return WanxImageModel.builder()
//                .apiKey(System.getenv("aliQwen-api"))
//                .modelName("wanx2.1-t2i-turbo")
//                .build();
//    }
}
