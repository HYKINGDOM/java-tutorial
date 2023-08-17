package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.SmsRequest;
import com.java.tutorial.project.domain.SmsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.plugin.core.PluginRegistry;

import java.util.Optional;

@RequiredArgsConstructor
public class SmsService {

    private final PluginRegistry<SmsPlugin, SmsRequest> pluginRegistry;

    public SmsResponse sendSms(SmsRequest smsRequest) {
        Optional<SmsPlugin> smsPlugin = pluginRegistry.getPluginFor(smsRequest);
        return smsPlugin.orElseThrow(
                () -> new RuntimeException("Sms plugin is not binder with type : 【" + smsRequest.getSmsType() + "】"))
            .sendSms(smsRequest);

    }
}


