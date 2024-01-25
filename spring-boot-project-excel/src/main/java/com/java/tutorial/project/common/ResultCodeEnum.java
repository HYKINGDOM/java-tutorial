package com.java.tutorial.project.common;

import lombok.Getter;

/**
 * @author meta
 */
@Getter
public enum ResultCodeEnum {
    /**
     * 操作成功
     */
    SUCCESS("200", "操作成功."), ERROR("500", "系统未知错误."), IMPORT_ERROR("500001", "导入错误."),
    ;

    private final String value;
    private final String label;

    private ResultCodeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

}
