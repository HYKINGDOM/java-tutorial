package com.java.demo.listener;

import javax.annotation.PostConstruct;
import javax.validation.Validator;

import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobValidateListener {

    @Autowired
    private Validator validator;

    @Autowired
    private Job job;

    @PostConstruct
    public void init() {
        JobValidationListener validationListener = new JobValidationListener();
        validationListener.setValidator(validator);
        //job.registerJobExecutionListener(validationListener);
    }
}