package com.java.tutorial.project.domain;

import lombok.Data;

import java.util.Date;


@Data
public class DataAuditEntity {

    private Long id;
    private String tableName;
    private String recordId;
    private String operator;
    private Date operateTime;
    private String changedFields;
    private String changeContent;
}
