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
 * LangChain4J测试V2
 *
 * @author meta
 */
@Slf4j
@RequestMapping("/langchain4j/V2")
@RestController
public class LangChain4JV2Controller {


    @Resource
    private ChatModel chatModelQwen;


    @Resource
    private ChatModel chatModelDeepSeek;


    String text = """
            In 1968, amidst the fading echoes of Independence Day,
            a child named John arrived under the calm evening sky.
            This newborn, bearing the surname Doe, marked the start of a new journey.
            He was welcomed into the world at 345 Whispering Pines Avenue
            a quaint street nestled in the heart of Springfield
            an abode that echoed with the gentle hum of suburban dreams and aspirations.
            """;

    /**
     * Qwen 模型
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/Qwen")
    public Result<ChatResponse> simpleChat(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {

        AiMessage aiMessage = AiMessage.from(prompt);

        ChatResponse result = chatModelQwen.chat(aiMessage);

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        TokenUsage tokenUsage = result.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return Result.success(result);
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

        ChatResponse result = chatModelQwen.chat(aiMessage);

        log.info("通过langchain4j调用 Qwen 模型返回结果：{}", JSON.toJSONString(result));

        TokenUsage tokenUsage = result.tokenUsage();
        log.info("Qwen 模型token消耗返回结果：{}", JSON.toJSONString(tokenUsage));

        return result.aiMessage().text();
    }
}
