package com.java.tutorial.project.common.enumtype;

import lombok.Getter;

@Getter
public enum ConnectionType {
    LIMIT(1,"limited_time_available_purchase"),

    SIGNAL(2,"start_up_signal"),
    ;
    private Integer code;

    private String desc;

    ConnectionType(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }
}
