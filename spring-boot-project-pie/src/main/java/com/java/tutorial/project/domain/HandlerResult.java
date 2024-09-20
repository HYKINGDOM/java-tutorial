package com.java.tutorial.project.domain;

import lombok.Data;

/**
 * 出参类型
 * @author meta
 */
@Data
public class HandlerResult {

    private Integer code;

    private Object data;

    private String msg;

}
