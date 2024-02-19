package com.java.tutorial.project.easyexcel;

import java.util.List;

/**
 * @author meta
 */
public interface ExcelCheckManager<T> {

    /**
     * @param objects
     * @return com.cec.moutai.common.easyexcel.ExcelCheckResult
     * @throws
     * @description: 校验方法
     * @author zhy
     * @date 2019/12/24 14:57
     */
    ExcelCheckResult checkImportExcel(List<T> objects);
}
