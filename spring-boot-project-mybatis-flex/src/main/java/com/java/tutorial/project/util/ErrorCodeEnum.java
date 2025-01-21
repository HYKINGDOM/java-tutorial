package com.java.tutorial.project.util;

import lombok.Getter;

/**
 * @author meta
 */

@Getter
public enum ErrorCodeEnum {
    /**
     * 公共
     */
    SUCCESS("200", "SUCCESS"),
    /**
     * 失败
     */
    FAIL("500", "FAIL"),
    /**
     * 异常
     */
    ERROR("501", "ERROR"),
    /**
     * 参数异常
     */
    PARAM_NULL_ERROR("502", "PARAM_NULL_ERROR");

    private final String code;

    private final String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
