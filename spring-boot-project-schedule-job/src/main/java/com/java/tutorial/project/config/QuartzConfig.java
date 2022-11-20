package com.java.tutorial.project.config;

import com.java.tutorial.project.job.SpringQuartzJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean("springQuartzJob")
    public JobDetail springQuartzJob() {
        return JobBuilder.newJob(SpringQuartzJob.class)
                .withIdentity("Date Time Job")
                .usingJobData("message", "start Quartz Job data")
                .storeDurably()
                .build();
    }


    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/50 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(springQuartzJob())
                .withIdentity("quartzTaskService")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}

