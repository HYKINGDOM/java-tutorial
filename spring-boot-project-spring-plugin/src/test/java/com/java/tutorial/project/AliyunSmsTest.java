package com.java.tutorial.project;

import com.java.tutorial.project.domain.SmsRequest;
import com.java.tutorial.project.domain.SmsResponse;
import com.java.tutorial.project.domain.SmsType;
import com.java.tutorial.project.service.SmsService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class AliyunSmsTest {

    @Resource
    private SmsService smsService;

    @Test
    public void testAliyunSms() {
        SmsRequest smsRequest =
            SmsRequest.builder().message("模拟使用阿里云短信发送").to("136000000001").smsType(SmsType.ALIYUN).build();

        SmsResponse smsResponse = smsService.sendSms(smsRequest);
        Assertions.assertThat(smsResponse.getSuccess()).isTrue();
        System.out.println(smsResponse);

    }

}
