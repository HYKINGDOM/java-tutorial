package com.java.util.javautil.DDD.alibaba;

import java.util.ArrayList;
import java.util.List;

public class TestPhoneNumber {

    public static void main(String[] args) {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber("01013227094567");
        phoneNumberList.add(phoneNumber);

        for (PhoneNumber number : phoneNumberList) {
            System.out.println(number.getPhoneNumber());
        }
    }
}
