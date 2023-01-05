package com.java.tutorial.project.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


//@Configuration
//public class HibernateValidationConfig {
//
//    @Bean
//    public MethodValidationPostProcessor methodValidationPostProcessor() {
//        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
//        processor.setValidator(validator());
//        return processor;
//    }
//
//    @Bean
//    public Validator validator() {
//        ValidatorFactory validatorFactory = Validation.byProvider(ValidatorFactory.class)
//                .configure()
////                .failFast(true)
//                .buildValidatorFactory();
//        Validator validator = validatorFactory.getValidator();
//        return validator;
//    }
//}

