package com.java.data.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author meta
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PassWordValidHandle implements ConstraintValidator<PassWordValid, String> {

    private String messages;

    private String pattern;

    @Override
    public void initialize(PassWordValid constraintAnnotation) {
        messages = constraintAnnotation.message();
        pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern compile = Pattern.compile(pattern);
        log.info(messages);
        return compile.matcher(value).find();
    }
}
