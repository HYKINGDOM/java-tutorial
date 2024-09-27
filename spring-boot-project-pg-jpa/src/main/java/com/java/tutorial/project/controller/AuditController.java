package com.java.tutorial.project.controller;

import com.java.tutorial.project.domain.CreatorInfo;
import com.java.tutorial.project.domain.SendEmailInfoEntity;
import org.javers.core.Javers;
import org.javers.core.diff.Change;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 审计信息控制层
 * @author meta
 */
@RestController
@RequestMapping(value = "/audit")
public class AuditController {

    private final Javers javers;

    @Autowired
    public AuditController(Javers javers) {
        this.javers = javers;
    }

    /**
     * 获取人员变更信息
     *
     * 本方法通过查询CreatorInfo类的相关变更来生成人员变更信息的JSON字符串 主要用于Web请求，提供人员变更的数据接口
     */
    @RequestMapping("/person")
    public String getPersonChanges() {
        // 创建一个QueryBuilder对象，用于指定查询的类
        QueryBuilder jqlQuery = QueryBuilder.byClass(CreatorInfo.class);

        // 执行查询，获取变更列表
        List<Change> changes = javers.findChanges(jqlQuery.build());

        // 将变更列表转换为JSON字符串并返回
        return javers.getJsonConverter().toJson(changes);
    }

    /**
     * 获取邮件变更信息
     * @return
     */
    @RequestMapping("/getSendEmailInfoEntityChanges")
    public String getSendEmailInfoEntityChanges() {
        // 创建一个QueryBuilder对象，用于指定查询的类
        QueryBuilder jqlQuery = QueryBuilder.byClass(SendEmailInfoEntity.class);

        // 执行查询，获取变更列表
        List<Change> changes = javers.findChanges(jqlQuery.build());

        // 将变更列表转换为JSON字符串并返回
        return javers.getJsonConverter().toJson(changes);
    }
}

