package com.java.tutorial.project.common.enumtype;

import lombok.Getter;

/**
 * @author meta
 */

@Getter
public enum SceneEnum {

    /**
     * 未连接
     */
    NO_CONN,
    /**
     * 断开
     */
    CLOSE,
    /**
     * 超时
     */
    EXPIRED,

    /**
     * 错误
     */
    ERROR
}
