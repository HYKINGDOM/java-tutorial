package com.java.tutorial.project.common.enumtype;

import lombok.Getter;

/**
 * 连接类型枚举
 *
 * @author meta
 */
@Getter
public enum ConnectionTypeEnum {

    /**
     * 普通消息
     */
    COMMON(0, "COMMON"),

    /**
     * gpt
     */
    LIMIT(1, "GPT"),

    /**
     * 站内信
     */
    SIGNAL(2, "MSG"),
    ;
    private final Integer code;

    private final String desc;

    ConnectionTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ConnectionTypeEnum getByCode(Integer code) {
        if (code == null) {
            throw new RuntimeException("code is null");
        }
        for (ConnectionTypeEnum connectionTypeEnum : ConnectionTypeEnum.values()) {
            if (connectionTypeEnum.getCode().equals(code)) {
                return connectionTypeEnum;
            }
        }
        throw new RuntimeException("no match code");
    }
}
