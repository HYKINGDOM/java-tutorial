package com.java.tutorial.project.config;

import com.java.tutorial.project.util.TraceIDUtil;
import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 测试ChatModelListener
 *
 * @author meta
 */
@Slf4j
public class TestChatModelListener implements ChatModelListener {
    @Override
    public void onRequest(ChatModelRequestContext requestContext) {

        // onRequest配置的k:v键值对，在onResponse阶段可以获得，上下文传递参数好用
        String traceId = TraceIDUtil.getTraceId();
        requestContext.attributes().put(TraceIDUtil.DEFAULT_TRACE_ID, traceId);
        log.info("请求参数requestContext:{}", requestContext + "\t" + traceId);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object object = responseContext.attributes().get(TraceIDUtil.DEFAULT_TRACE_ID);
        log.info("返回结果responseContext:{}", object);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("请求异常ChatModelErrorContext:{}", errorContext);
    }
}

