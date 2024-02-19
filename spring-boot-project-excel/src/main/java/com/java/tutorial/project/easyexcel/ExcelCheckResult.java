package com.java.tutorial.project.easyexcel;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author meta
 */
@Data
public class ExcelCheckResult<T> {
    private List<T> successDtos;

    private List<ExcelCheckErrDto<T>> errDtos;

    public ExcelCheckResult(List<T> successDtos, List<ExcelCheckErrDto<T>> errDtos) {
        this.successDtos = successDtos;
        this.errDtos = errDtos;
    }

    public ExcelCheckResult(List<ExcelCheckErrDto<T>> errDtos) {
        this.successDtos = new ArrayList<>();
        this.errDtos = errDtos;
    }
}
