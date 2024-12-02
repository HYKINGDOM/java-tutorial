package com.java.tutorial.project.domain;

import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * @author meta
 */

@AllArgsConstructor
public enum OrderTypeEnum {
    /**
     * to_c
     */
    TO_C(1, "to_c"),

    /**
     * to_b
     */
    TO_B(2, "to_b");

    /** 订单类型 */
    public final Integer code;
    /** 订单处理的节点ID */
    public final String chainId;

    public static String getChainId(Integer code) {
        return Arrays.stream(OrderTypeEnum.values()).filter(orderTypeEnum -> orderTypeEnum.code.equals(code))
            .findFirst().map(e -> e.chainId).orElseThrow(() -> new RuntimeException("不支持的订单类型"));
    }
}

