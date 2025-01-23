package com.java.tutorial.project.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AuditLog {
    private Long id;
    private String tableName;      // 表名
    private String operatorId;     // 操作人ID
    private Date operateTime;      // 操作时间
    private String changeContent;  // 变更内容(JSON格式)
    private String changeFields;   // 变更字段列表
}
