package com.java.demo.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 启动job
 *
 * @author meta
 */
@Component
@Slf4j
public class StartJob {

    @Autowired
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;

    @PostConstruct
    public void startJob() {
        JobParameters jobParameters =
            new JobParametersBuilder().addString("uuid", UUID.randomUUID().toString()).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException |
            JobInstanceAlreadyCompleteException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        log.info("job invoked");
    }
}
