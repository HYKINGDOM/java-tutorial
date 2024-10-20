package com.java.tutorial.project.service;

import com.java.tutorial.project.common.vo.ContentVo;
import com.java.tutorial.project.common.vo.MessageVo;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Set;

/**
 * @author meta
 */
public interface SseEmitterService {
    /**
     * 创建连接
     *
     * @param type     连接类型
     * @param clientId 客户端ID
     */
    SseEmitter createConnect(String clientId, Integer type);

    /**
     * 根据客户端id获取SseEmitter对象
     *
     * @param clientId 客户端ID
     */
    SseEmitter getSseEmitterByClientId(String clientId);

    /**
     * 获取所有客户端ID
     * @return
     */
    Set<String> getAllClient();

    /**
     * 构建SseEmitter对象
     * @param clientId
     * @return
     */
    SseEmitter buildSseEmitter(String clientId);

    /**
     * 发送消息给所有客户端
     *
     * @param contentVo 消息内容
     */
    void sendMessageToAllClient(ContentVo contentVo);

    /**
     * 给指定客户端发送消息
     *
     * @param messageVo 消息内容
     */
    void sendMessageToOneClient(MessageVo messageVo);

    /**
     * 给多个客户端发送消息
     * @param messageVo
     */
    void sendMessageToManyClient(MessageVo messageVo);

    /**
     * 关闭连接
     *
     * @param clientId 客户端ID
     */
    void closeConnect(String clientId);
}
