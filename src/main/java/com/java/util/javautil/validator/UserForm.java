package com.java.util.javautil.validator;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class UserForm {

    /**
     * id
     */
    @Null(message = "新增时id必须为空", groups = {Insert.class})
    @NotNull(message = "更新时id不能为空", groups = {Update.class})
    private String id;

    /**
     * 类型
     */
    @NotEmpty(message = "姓名不能为空" , groups = {Insert.class})
    private String name;

    /**
     * 年龄
     */
    @NotEmpty(message = "年龄不能为空" , groups = {Insert.class})
    private String age;

}

