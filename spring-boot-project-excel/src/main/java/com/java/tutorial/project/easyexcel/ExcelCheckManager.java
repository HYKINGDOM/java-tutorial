package com.java.tutorial.project.easyexcel;

import java.util.List;

/**
 * @author meta
 */
public interface ExcelCheckManager<T> {

    /**
     * @description: 校验方法
     * @param objects
     * @throws
     * @return com.cec.moutai.common.easyexcel.ExcelCheckResult
     * @author zhy
     * @date 2019/12/24 14:57
     */
    ExcelCheckResult checkImportExcel(List<T> objects);
}