package com.java.demo.listener;

import javax.validation.Validator;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameters;

public class JobValidationListener implements JobExecutionListener {

    private Validator validator;

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        JobParameters parameters = jobExecution.getJobParameters();
//        BatchJobParameterValidator validator = new BatchJobParameterValidator(parameters);
//        validator.validate();
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

    }
}
