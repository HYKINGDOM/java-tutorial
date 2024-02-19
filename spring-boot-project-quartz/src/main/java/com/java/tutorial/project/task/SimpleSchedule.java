package com.java.tutorial.project.task;

import com.java.tutorial.project.job.SchedulerJob0;
import com.java.tutorial.project.job.SchedulerJob1;
import com.java.tutorial.project.job.SchedulerJob2;
import com.java.tutorial.project.job.SchedulerJob3;
import com.java.tutorial.project.job.SchedulerJob4;
import com.java.tutorial.project.job.SchedulerJob5;
import com.java.tutorial.project.job.SchedulerJob6;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hy
 */
@Configuration
public class SimpleSchedule {

    public static final String CRON_EXPRESSION = "0 0/1 * * * ? *";

    @Bean
    public JobDetail job0() {
        return JobBuilder.newJob(SchedulerJob0.class).withIdentity("job0").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger0() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job0()).withIdentity("job0 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job1() {
        return JobBuilder.newJob(SchedulerJob1.class).withIdentity("job1").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger1() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job1()).withIdentity("job1 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job2() {
        return JobBuilder.newJob(SchedulerJob2.class).withIdentity("job2").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger2() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job2()).withIdentity("job2 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job3() {
        return JobBuilder.newJob(SchedulerJob3.class).withIdentity("job3").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger3() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job3()).withIdentity("job3 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job4() {
        return JobBuilder.newJob(SchedulerJob4.class).withIdentity("job4").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger4() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job4()).withIdentity("job4 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job5() {
        return JobBuilder.newJob(SchedulerJob5.class).withIdentity("job5").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger5() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job5()).withIdentity("job5 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

    @Bean
    public JobDetail job6() {
        return JobBuilder.newJob(SchedulerJob6.class).withIdentity("job6").storeDurably().usingJobData("k1", "v1")
            .build();
    }

    @Bean
    public Trigger cronJobTrigger7() {
        // 每隔1分钟执行一次
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);
        // Trigger 构造器
        return TriggerBuilder.newTrigger().forJob(job6()).withIdentity("job6 Trigger").withSchedule(scheduleBuilder)
            .build();
    }

}
