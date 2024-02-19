package com.java.tutorial.project.service;

import com.java.tutorial.project.common.vo.MessageVo;

import java.util.Set;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface SseEmitterService {
    /**
     * 创建连接
     *
     * @param clientId 客户端ID
     */
    SseEmitter createConnect(String clientId, Integer type);

    /**
     * 根据客户端id获取SseEmitter对象
     *
     * @param clientId 客户端ID
     */
    SseEmitter getSseEmitterByClientId(String clientId);

    Set<String> getAllClient();

    /**
     * 发送消息给所有客户端
     *
     * @param msg 消息内容
     */
    void sendMessageToAllClient(String msg);

    /**
     * 给指定客户端发送消息
     *
     * @param clientId 客户端ID
     * @param msg      消息内容
     */
    void sendMessageToOneClient(String clientId, String msg, Integer type);

    void sendMessageToManyClient(MessageVo messageVo);

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     */
    void closeConnect(String clientId);
}
