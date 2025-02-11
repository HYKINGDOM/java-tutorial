package com.java.tutorial.project.controller;

import io.github.pigmesh.ai.deepseek.core.DeepSeekClient;
import io.github.pigmesh.ai.deepseek.core.chat.ChatCompletionResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;

/**
 * @author meta
 */
@Controller
public class DeepSeekApiController {

    @Resource
    private DeepSeekClient deepSeekClient;

    @CrossOrigin
    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ChatCompletionResponse> chat(String prompt) {
        System.out.println("prompt: " + prompt);
        return deepSeekClient.chatFluxCompletion(prompt);
    }

    @CrossOrigin
    @GetMapping("/sse")
    public String sse() {
        System.out.println("sse");
        return "sse";
    }
}
