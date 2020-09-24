package com.java.util.javautil.validator;

import lombok.Data;

@Data
public class CustomForm {

    /**
     * 电话号码
     */
    @Phone
    private String phone;

}

