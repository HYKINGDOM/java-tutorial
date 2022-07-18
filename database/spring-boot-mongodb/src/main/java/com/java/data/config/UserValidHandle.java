package com.java.data.config;


import com.java.data.controller.request.UserRequest;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.util.Set;

public class UserValidHandle implements ConstraintValidator<UserValid, UserRequest> {

    @Override
    public void initialize(UserValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequest value, ConstraintValidatorContext context) {
        String passWord = value.getPassWord();
        String userAccountNum = value.getUserAccountNum();
        return passWord != null && userAccountNum != null;
    }
}
