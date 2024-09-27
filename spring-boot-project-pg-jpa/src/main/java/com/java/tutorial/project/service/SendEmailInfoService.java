package com.java.tutorial.project.service;

import com.java.tutorial.project.domain.SendEmailInfoEntity;

public interface SendEmailInfoService {

    void saveSendEmailInfoEntity(SendEmailInfoEntity sendEmailInfoEntity);

    SendEmailInfoEntity getSendEmailInfoEntityById(Integer id);

    void updateSendEmailInfoEntity(SendEmailInfoEntity sendEmailInfoEntity);

}
