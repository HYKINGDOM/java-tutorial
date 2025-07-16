package com.java.tutorial.project.controller;

import com.java.tutorial.project.util.Result;
import dev.langchain4j.model.chat.ChatModel;
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
@RequestMapping("/langchain4j/V1")
@RestController
public class LangChain4JController {


    @Resource
    private ChatModel chatModelQwen;

    /**
     * simpleChat
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/simpleChat")
    public Result<String> simpleChat(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {
        String result = chatModelQwen.chat(prompt);

        log.info("通过langchain4j调用模型返回结果：{}", result);

        return Result.success(result);
    }
}
