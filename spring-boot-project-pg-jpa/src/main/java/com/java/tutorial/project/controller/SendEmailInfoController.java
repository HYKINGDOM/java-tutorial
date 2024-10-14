package com.java.tutorial.project.controller;

import com.java.tutorial.project.domain.SendEmailInfoEntity;
import com.java.tutorial.project.service.SendEmailInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author meta
 */
@RestController
@RequestMapping("/send-email-info")
public class SendEmailInfoController {

    @Resource
    private SendEmailInfoService sendEmailInfoService;

    @RequestMapping("/save")
    public void save(@RequestBody SendEmailInfoEntity sendEmailInfoEntity) {
        sendEmailInfoService.saveSendEmailInfoEntity(sendEmailInfoEntity);
    }

    @RequestMapping("/update")
    public void update(@RequestBody SendEmailInfoEntity sendEmailInfoEntity) {
        sendEmailInfoService.updateSendEmailInfoEntity(sendEmailInfoEntity);
    }

    @RequestMapping("/query")
    public SendEmailInfoEntity query(@RequestParam("id") Integer id) {
        return sendEmailInfoService.getSendEmailInfoEntityById(id);
    }
}
