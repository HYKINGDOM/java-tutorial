package com.java.tutorial.project.config;

import com.java.tutorial.project.job.SpringQuartzJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author meta
 */
@Configuration
public class QuartzConfig implements SchedulingConfigurer {

    @Qualifier("ttlScheduledExecutorService")
    @Resource
    private ScheduledExecutorService defaultScheduledExecutorService;

    @Bean("springQuartzJob")
    public JobDetail springQuartzJob() {
        return JobBuilder.newJob(SpringQuartzJob.class).withIdentity("Date Time Job")
            .usingJobData("message", "start Quartz Job data").storeDurably().build();
    }

    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/50 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(springQuartzJob()).withIdentity("quartzTaskService")
            .withSchedule(cronScheduleBuilder).build();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(defaultScheduledExecutorService);
    }
}

