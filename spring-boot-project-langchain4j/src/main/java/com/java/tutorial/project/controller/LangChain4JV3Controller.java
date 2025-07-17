package com.java.tutorial.project.controller;

import com.alibaba.fastjson.JSON;
import com.java.tutorial.project.util.Result;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * LangChain4JController
 *
 * @author meta
 */
@Slf4j
@RequestMapping("/langchain4j/V3")
@RestController
public class LangChain4JV3Controller {


    @Resource
    private ChatModel chatStreamingModelQwen;


    @Resource
    private ChatModel chatStreamingModelDeepSeek;

    /**
     * Qwen 模型
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/Qwen")
    public Result<ChatResponse> simpleChat(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {


        AiMessage aiMessage = AiMessage.from(prompt);

        ChatResponse chatResponse = chatStreamingModelQwen.chat(aiMessage, new StreamingChatResponseHandler() {

            @Override
            public void onPartialResponse(String partialResponse) {
                log.info("onPartialResponse: {}", partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                log.info("onCompleteResponse: {}", completeResponse);
            }

            @Override
            public void onError(Throwable error) {
                log.error("onError: {}", error.getMessage());
            }
        });

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(chatResponse));

        TokenUsage tokenUsage = chatResponse.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return Result.success();
    }


    /**
     * DeepSeek
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/deepseek")
    public String deepseekCall(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {

        AiMessage aiMessage = AiMessage.from(prompt);

        ChatResponse result = chatStreamingModelDeepSeek.chat(aiMessage);

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        TokenUsage tokenUsage = result.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return result.aiMessage().text();
    }
}
