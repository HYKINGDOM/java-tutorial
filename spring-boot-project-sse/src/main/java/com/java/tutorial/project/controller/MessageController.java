package com.java.tutorial.project.controller;

import com.java.tutorial.project.common.vo.Message;
import com.java.tutorial.project.service.impl.MessageSinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageSinkService messageSinkService;
    /**
     * 自增ID生成器
     */
    private final AtomicInteger idGenerator = new AtomicInteger(0);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 发送消息接口（模拟客户端发送消息）
     */
    @GetMapping("/send")
    public Mono<Message> sendMessage(@RequestParam String message) {
        // 固定作者为System
        Message msg = new Message(
                idGenerator.incrementAndGet(),
                "System",
                LocalDateTime.now().format(TIME_FORMATTER),
                message
        );
        return messageSinkService.saveMessage(Mono.just(msg));
    }

    /**
     * SSE消息流接口（供前端订阅）
     * 注意：MediaType必须设置为TEXT_EVENT_STREAM_VALUE
     */
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Message> messageStream() {
        return messageSinkService.getMessageStream();
    }
}