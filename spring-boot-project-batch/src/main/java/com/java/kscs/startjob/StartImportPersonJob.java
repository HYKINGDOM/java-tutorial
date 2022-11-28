package com.java.kscs.startjob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 启动job
 *
 * @author hy
 */
@Component
@Slf4j
public class StartImportPersonJob {

    @Autowired
    private Job importPersonJob;
    @Autowired
    private JobLauncher jobLauncher;

    @PostConstruct
    public void startJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("importDate", LocalDate.of(2022, 8, 31).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .toJobParameters();
        JobExecution execution = jobLauncher.run(importPersonJob, jobParameters);
        log.info("job invoked");
    }
}
