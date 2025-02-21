package com.java.tutorial.project.controller;

import com.java.tutorial.project.util.Result;
import com.java.tutorial.project.domain.EmailRepliedInfoEntity;
import com.java.tutorial.project.service.EmailRepliedInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件回复控制层
 *
 * @author meta
 */
@RestController
@RequestMapping("/emailRepliedInfo")
public class EmailRepliedInfoController {

    @Resource
    private EmailRepliedInfoService emailRepliedInfoService;

    /**
     * 保存电子邮件回复信息
     *
     * @param emailRepliedInfoEntity 电子邮件回复信息实体，通过请求体传递
     * @return 返回操作结果，包含成功消息
     */
    @PostMapping
    public Result<String> save(@RequestBody EmailRepliedInfoEntity emailRepliedInfoEntity) {
        // 调用服务层方法保存电子邮件回复信息
        emailRepliedInfoService.save(emailRepliedInfoEntity);
        // 返回成功操作结果
        return Result.success("成功");
    }
}
