package com.java.tutorial.tool.domain;


import com.alibaba.fastjson2.JSONObject;
import com.java.tutorial.tool.util.TraceIDUtil;

import java.time.LocalDateTime;


/**
 * @author HY
 */
public class MessageDtoBuilder {


    /**
     * 设置消息生产端的对象并返回
     * 构建过程中设置TraceID
     * @param businessId
     * @param payload
     * @param version
     * @return
     */
    public static MessageDto builderMessageDto(Long businessId, Object payload, String version) {
        return MessageDto.builder()
                .businessId(businessId)
                .payload(payload)
                .version(version)
                .traceId(TraceIDUtil.getTraceId())
                .currentDateTime(LocalDateTime.now())
                .build();
    }


    /**
     * 将消息的消费端接收的消息进行转换
     * 转换过程中设置TraceID
     * @param messageObject
     * @return
     */
    public static Object parseObjectAndGetPayload(String messageObject) {
        MessageDto messageDto = JSONObject.parseObject(messageObject, MessageDto.class);
        //将封装的变量在此取出并设置
        TraceIDUtil.setTraceIdToMdcAndTtl(messageDto.getTraceId());
        return messageDto.getPayload();
    }
}
