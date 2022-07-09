package com.java.validate.domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, Object> {

    @Override
    public boolean isValid(Object telephone, ConstraintValidatorContext constraintValidatorContext) {
        String pattern = "^1[3|4|5|7|8]\\d{9}$";
        return Pattern.matches(pattern, telephone.toString());
    }
}

