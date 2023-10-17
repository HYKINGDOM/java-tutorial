package com.java.tutorial.project.service;

import com.java.tutorial.project.common.enumtype.SceneEnum;

public interface MessageService {
    void sendMessage(String clientId, Integer type, SceneEnum scene);
}
