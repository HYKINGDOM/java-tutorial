package com.java.util.javautil.DDD.alibaba;

import javax.validation.ValidationException;
import java.util.Arrays;

/**
 * @author
 */
public class PhoneNumber {

    private final String number;

    public String getPhoneNumber() {
        return number;
    }


    public PhoneNumber(String number) {
        if (number == null) {
            throw new ValidationException("number is null");
        }
        if (isValid(number)) {
            throw new ValidationException("number not valid");
        }
        this.number = number;
    }

    public String getAreaCode() {
        for (int i = 0; i < number.length(); i++) {
            String prefix = number.substring(0, i);
            if (isAreaCode(prefix)) {
                return prefix;
            }
        }
        return null;
    }

    private static boolean isAreaCode(String prefix) {
        String[] areas = new String[]{"0571", "021", "010"};
        return Arrays.asList(areas).contains(prefix);
    }

    public static boolean isValid(String number) {
        String pattern = "^0?[1-9]{2,3}-?\\d{8}$";
        return number.matches(pattern);
    }

}
