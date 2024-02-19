package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.SmsRequest;
import com.java.tutorial.project.domain.SmsResponse;
import org.springframework.plugin.core.Plugin;

public interface SmsPlugin extends Plugin<SmsRequest> {

    SmsResponse sendSms(SmsRequest smsRequest);

}


