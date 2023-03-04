package com.java.tutorial.project.controller.validator;


import com.java.tutorial.project.controller.request.UserRequest;
import com.java.tutorial.project.controller.validator.annotation.UserValid;
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

        ConstraintValidatorContextImpl cvc = (ConstraintValidatorContextImpl) context;
        Set<Class<? extends Payload>> payload = cvc.getConstraintDescriptor().getPayload();
        for (Class<? extends Payload> aClass : payload) {
            System.out.println(aClass.toString());
        }
        String passWord = value.getPassWord();
        String userAccountNum = value.getUserAccountNum();
        return passWord != null && userAccountNum != null;
    }
}
