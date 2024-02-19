package com.java.tutorial.project.domain;

import lombok.Getter;

@Getter
public enum SmsType {

    ALIYUN("ALIYUN");

    private String type;

    SmsType(String type) {
        this.type = type;
    }
}
