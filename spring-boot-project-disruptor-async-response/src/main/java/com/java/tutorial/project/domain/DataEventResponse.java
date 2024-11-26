package com.java.tutorial.project.domain;

import lombok.Data;

/**
 * 返回参数封装类
 *
 * @author meta
 */
@Data
public class DataEventResponse {

    private String data;

    private String userId;

    private String traceId;

    private String number;

    private String threadName;
}
