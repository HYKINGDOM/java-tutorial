package com.java.tutorial.project.exception;

/**
 * 验证码异常
 *
 * @author ruoyi
 */
public class ValidateCodeException extends Exception {
    private static final long serialVersionUID = 3887472968823615091L;

    public ValidateCodeException() {
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}