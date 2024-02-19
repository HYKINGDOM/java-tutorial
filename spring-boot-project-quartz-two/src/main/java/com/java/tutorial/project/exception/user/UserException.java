package com.java.tutorial.project.exception.user;

import com.java.tutorial.project.exception.BaseException;

/**
 * 用户信息异常类
 *
 * @author meta
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
