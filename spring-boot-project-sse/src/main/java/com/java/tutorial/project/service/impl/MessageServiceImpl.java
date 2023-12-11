package com.java.tutorial.project.service.impl;

import static com.alibaba.fastjson2.util.DateUtils.DateTimeFormatPattern.DATE_TIME_FORMAT_19_DASH;

import com.alibaba.fastjson2.util.DateUtils;
import com.java.tutorial.project.client.MsgClient;
import com.java.tutorial.project.common.constant.CommonMessageConstant;
import com.java.tutorial.project.common.constant.LimitMessageConstant;
import com.java.tutorial.project.common.constant.SignalMessageConstant;
import com.java.tutorial.project.common.enumtype.SceneEnum;
import com.java.tutorial.project.service.MessageService;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Resource
    private MsgClient msgClient;

    @Override
    public void sendMessage(String clientId, Integer type, SceneEnum scene) {
        switch (type) {
            case 1:
                sendLimit(clientId, scene);
                break;
            case 2:
                sendSignal(clientId, scene);
                break;
            case 0:
                sendCommon(clientId);
            default:
                break;
        }
    }

    private void sendCommon(String clientId) {
        String title = "未建立连接";
        String content = String.format(CommonMessageConstant.content, clientId,
            DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        msgClient.groupSend(title, content);
    }

    private void sendSignal(String clientId, SceneEnum scene) {
        String content = "";
        String title = SignalMessageConstant.title;
        if (SceneEnum.ERROR == scene) {
            content = String.format(SignalMessageConstant.content1, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.EXPIRED == scene) {
            content = String.format(SignalMessageConstant.content3, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.NO_CONN == scene) {
            content = String.format(SignalMessageConstant.content2, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.CLOSE == scene) {
            content = String.format(SignalMessageConstant.content4, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        }
        msgClient.groupSend(title, content);
    }

    private void sendLimit(String clientId, SceneEnum scene) {
        String content = "";
        String title = LimitMessageConstant.TITLE;
        if (SceneEnum.ERROR == scene) {
            content = String.format(LimitMessageConstant.content1, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.EXPIRED == scene) {
            content = String.format(LimitMessageConstant.content3, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.NO_CONN == scene) {
            content = String.format(LimitMessageConstant.content2, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        } else if (SceneEnum.CLOSE == scene) {
            content = String.format(LimitMessageConstant.content4, clientId,
                DateUtils.format(new Date(), DATE_TIME_FORMAT_19_DASH.name()));
        }
        msgClient.groupSend(title, content);
    }
}
