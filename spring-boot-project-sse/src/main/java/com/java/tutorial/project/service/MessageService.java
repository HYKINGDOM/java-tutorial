package com.java.tutorial.project.service;

import com.java.tutorial.project.common.enumtype.SceneEnum;

/**
 * @author meta
 */
public interface MessageService {

    /**
     * 发送消息
     * @param clientId
     * @param type
     * @param scene
     */
    void sendMessage(String clientId, Integer type, SceneEnum scene);
}
