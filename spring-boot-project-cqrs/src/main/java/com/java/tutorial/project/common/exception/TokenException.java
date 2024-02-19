package com.java.tutorial.project.common.exception;

/**
 * 令牌刷新值过期异常
 */
public final class TokenException extends RuntimeException {

    public TokenException() {
        super("令牌刷新值过期");
    }

}
