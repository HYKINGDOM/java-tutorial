package com.java.util.javautil.validator;


import lombok.Data;

import javax.validation.constraints.Pattern;


public class PhoneForm {

    /**
     * 电话号码
     */
    @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$" , message = "电话号码有误")
    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "PhoneForm{" +
                "phone='" + phone + '\'' +
                '}';
    }
}
