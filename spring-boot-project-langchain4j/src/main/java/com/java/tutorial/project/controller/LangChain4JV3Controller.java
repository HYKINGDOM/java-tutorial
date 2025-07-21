package com.java.tutorial.project.controller;

import com.java.tutorial.project.util.Result;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * LangChain4J测试V3
 *
 * @author meta
 */
@Slf4j
@RequestMapping("/langchain4j/V3")
@RestController
public class LangChain4JV3Controller {

    @Resource
    private StreamingChatModel chatStreamingModelQwen;

    @Resource
    private StreamingChatModel chatStreamingModelDeepSeek;

    /**
     * 使用ConcurrentHashMap存储会话ID与会话历史的映射
     */
    private final Map<String, List<ChatMessage>> CONVERSATION_HISTORY = new ConcurrentHashMap<>();

    /**
     * 创建新会话
     *
     * @return 会话ID
     */
    @GetMapping("/conversation/create")
    public Result<String> createConversation() {
        String conversationId = UUID.randomUUID().toString();
        CONVERSATION_HISTORY.put(conversationId, new ArrayList<>());
        return Result.success(conversationId);
    }

    /**
     * Qwen 模型 - SSE流式响应（支持连续对话）
     *
     * @param conversationId 会话ID
     * @param prompt         用户输入
     * @return SseEmitter对象用于流式传输响应
     */
    @GetMapping(value = "/langchain4j/Qwen/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter chatWithHistory(@RequestParam(value = "conversationId") String conversationId,
        @RequestParam(value = "prompt") String prompt) {

        SseEmitter emitter = new SseEmitter(60_000L);

        // 添加原子变量用于追踪发射器完成状态
        AtomicBoolean emitterCompleted = new AtomicBoolean(false);
        // 用于取消模型响应的Future
        CompletableFuture<Void> modelResponseFuture = new CompletableFuture<>();
        try {
            // 获取或创建会话历史
            List<ChatMessage> messages = CONVERSATION_HISTORY.computeIfAbsent(conversationId, k -> new ArrayList<>());

            // 添加用户新消息到历史
            UserMessage userMessage = UserMessage.from(prompt);
            messages.add(userMessage);

            // 调用模型并设置流式响应处理器，保存Future用于取消
            chatStreamingModelQwen.chat(messages, new StreamingChatResponseHandler() {
                @Override
                public void onPartialResponse(String partialResponse) {

                    log.info("接收到部分响应：{}", partialResponse);

                    // 使用原子变量追踪发射器状态，避免重复操作
                    if (!emitterCompleted.get()) {
                        try {
                            emitter.send(
                                SseEmitter.event().data(partialResponse));
                        } catch (IOException e) {
                            log.error("SSE发送部分响应失败", e);
                            if (emitterCompleted.compareAndSet(false, true)) {
                                emitter.completeWithError(e);
                                // 取消模型响应以停止后续事件
                                modelResponseFuture.cancel(true);
                            }
                        }
                    }
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse) {
                    // 将AI回复添加到会话历史
                    AiMessage aiMessage = completeResponse.aiMessage();
                    messages.add(aiMessage);
                    emitter.complete();
                    log.info("完整响应处理完成");
                }

                @Override
                public void onError(Throwable error) {
                    log.error("模型调用过程中发生错误", error);
                    emitter.completeWithError(error);
                }
            });

            // 设置SSE流回调
            emitter.onCompletion(() -> log.info("SSE流正常完成"));
            emitter.onTimeout(() -> {
                log.warn("SSE流超时");
                emitter.complete();
            });
            emitter.onError(error -> log.error("SSE流发生错误", error));

        } catch (Exception e) {
            log.error("初始化SSE流失败", e);
            emitter.completeWithError(e);
        }

        return emitter;
    }

    /**
     * 获取会话历史
     *
     * @param conversationId 会话ID
     * @return 会话历史列表
     */
    @GetMapping("/conversation/history")
    public Result<List<ChatMessage>> getConversationHistory(@RequestParam("conversationId") String conversationId) {

        List<ChatMessage> history = CONVERSATION_HISTORY.getOrDefault(conversationId, Collections.emptyList());
        return Result.success(history);
    }

    /**
     * 清除会话历史
     *
     * @param conversationId 会话ID
     * @return 操作结果
     */
    @DeleteMapping("/conversation/clear")
    public Result<Void> clearConversationHistory(@RequestParam("conversationId") String conversationId) {

        CONVERSATION_HISTORY.remove(conversationId);
        return Result.success();
    }

    /**
     * DeepSeek
     *
     * @param prompt
     * @return
     */
    @GetMapping(value = "/langchain4j/deepseek")
    public Result deepseekCall(@RequestParam(value = "prompt", defaultValue = "你是谁?") String prompt) {

        chatStreamingModelDeepSeek.chat(prompt, new StreamingChatResponseHandler() {

            @Override
            public void onPartialResponse(String partialResponse) {
                log.info("deepseek onPartialResponse: {}", partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                log.info("deepseek onCompleteResponse: {}", completeResponse);
            }

            @Override
            public void onError(Throwable error) {
                log.error("deepseek onError: {}", error.getMessage());
            }
        });

        return Result.success();
    }
}
