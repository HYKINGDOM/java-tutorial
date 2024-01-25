package com.java.tutorial.project.easyexcel;

import lombok.Data;

/**
 * @author meta
 */
@Data
public class ExcelCheckErrDto<T> {
    private T t;

    private String errMsg;

    public ExcelCheckErrDto(T t, String errMsg){
        this.t = t;
        this.errMsg = errMsg;
    }
}
