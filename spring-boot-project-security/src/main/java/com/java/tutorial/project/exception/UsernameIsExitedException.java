package com.java.tutorial.project.exception;

/**
 * @author yihur
 */
public class UsernameIsExitedException extends ServiceException {

    public UsernameIsExitedException(String msg) {
        super(msg);
    }

    public UsernameIsExitedException(String msg, Throwable t) {
        super(msg, t);
    }
}