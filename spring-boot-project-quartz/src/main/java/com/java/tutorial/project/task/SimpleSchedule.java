package com.java.tutorial.project.task;

import com.java.tutorial.project.job.Job1;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kscs
 */
@Configuration
public class SimpleSchedule {

    @Bean
    public JobDetail job1() {
        return JobBuilder.newJob(Job1.class).withIdentity("job1").storeDurably().usingJobData("k1", "v1").build();
    }

    @Bean
    public Trigger cronJobTrigger() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *");
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job1()).withIdentity("job1Trigger").withSchedule(scheduleBuilder)
            .build();
    }

}
