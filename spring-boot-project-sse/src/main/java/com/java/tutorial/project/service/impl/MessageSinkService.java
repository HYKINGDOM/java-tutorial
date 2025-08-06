package com.java.tutorial.project.service.impl;


import com.java.tutorial.project.common.vo.Message;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class MessageSinkService {

    /**
     * 注入Sink
     */
    @Resource
    private Sinks.Many<Message> messageSink;

    /**
     * 保存消息并推送到Sink
     */
    public Mono<Message> saveMessage(Mono<Message> messageMono) {
        return messageMono.doOnNext(message -> {
            // 尝试发送消息到Sink
            Sinks.EmitResult result = messageSink.tryEmitNext(message);
            if (result.isFailure()) {
                System.err.println("消息发送失败: " + result);
            }
        });
    }

    /**
     * 提供消息流（供前端订阅）
     */
    public Flux<Message> getMessageStream() {
        return messageSink.asFlux();
    }
}
