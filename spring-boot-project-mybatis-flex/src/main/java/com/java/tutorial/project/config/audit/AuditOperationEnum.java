package com.java.tutorial.project.config.audit;

import lombok.Getter;

/**
 * @author meta
 */
@Getter
public enum AuditOperationEnum {

    /**
     * 插入
     */
    insert("insert"),
    /**
     * 更新
     */
    update("update"),
    /**
     * 删除
     */
    delete("delete");

    private final String name;

    AuditOperationEnum(String name) {
        this.name = name;
    }
}
