package com.java.util.javautil.DDD.alibaba;

import javax.validation.ValidationException;

public class UserId {

    private final String userId;


    public String getUserId() {
        return userId;
    }

    public UserId(String userId) {
        if (userId == null) {
            throw new ValidationException("userid is null");
        }

        if (isValid(userId)) {
            throw new ValidationException("userid is not valid");
        }
        this.userId = userId;
    }


    private boolean isValid(String userId) {
        int length = userId.length();
        return length <= 10;
    }
}
