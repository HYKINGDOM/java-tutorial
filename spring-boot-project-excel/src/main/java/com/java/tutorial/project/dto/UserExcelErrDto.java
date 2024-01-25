package com.java.tutorial.project.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author meta
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class UserExcelErrDto extends UserExcelDto {


    //错误信息
    @ExcelProperty(index = 4,value = "错误信息")
    @ColumnWidth(50)
    private String errMsg;


}
