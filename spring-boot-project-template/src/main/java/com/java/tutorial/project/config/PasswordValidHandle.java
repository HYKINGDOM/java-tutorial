package com.java.tutorial.project.config;

import com.java.tutorial.project.config.annotations.PasswordValid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author yi.hu
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class PasswordValidHandle implements ConstraintValidator<PasswordValid, String> {

    private String messages;

    private String pattern;

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
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
