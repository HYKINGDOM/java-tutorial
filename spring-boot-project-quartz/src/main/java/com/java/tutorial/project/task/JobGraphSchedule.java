package com.java.tutorial.project.task;

import com.java.tutorial.project.job.SchedulerJob0;
import com.java.tutorial.project.job.SchedulerJob1;
import com.java.tutorial.project.job.SchedulerJob2;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.impl.matchers.OrMatcher;
import org.quartz.listeners.JobChainingJobListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class JobGraphSchedule {

    public static final String CRON_EXPRESSION = "0 0/1 * * * ? *";
    private CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(CRON_EXPRESSION);

    //    @Bean
    //    public JobDetail job0() {
    //        return JobBuilder.newJob(SchedulerJob0.class).withIdentity("job0").storeDurably().usingJobData("k1", "v1")
    //            .build();
    //    }
    //
    //    @Bean
    //    public JobDetail job1() {
    //        return JobBuilder.newJob(SchedulerJob1.class).withIdentity("job1").storeDurably().usingJobData("k1", "v1")
    //            .build();
    //    }
    //
    //    @Bean
    //    public JobDetail job2() {
    //        return JobBuilder.newJob(SchedulerJob2.class).withIdentity("job2").storeDurably().usingJobData("k1", "v1")
    //            .build();
    //    }
    //
    //    @Autowired
    //    private Scheduler scheduler;
    //
    //    @EventListener(ContextRefreshedEvent.class)
    //    public void init() {
    //        JobChainingJobListener jobChainingJobListener = new JobChainingJobListener("jobChain");
    //        jobChainingJobListener.addJobChainLink(job0().getKey(), job1().getKey());
    //        jobChainingJobListener.addJobChainLink(job1().getKey(), job2().getKey());
    //        try {
    //            scheduler.getListenerManager().addJobListener(jobChainingJobListener,
    //                OrMatcher.or(KeyMatcher.keyEquals(job0().getKey()), KeyMatcher.keyEquals(job1().getKey())));
    //        } catch (SchedulerException se) {
    //            se.printStackTrace();
    //        }
    //    }
    //
    //
    //
    //    @Bean
    //    public CronTrigger cronTrigger() {
    //        return TriggerBuilder.newTrigger()
    //            .forJob(job0())
    //            .withIdentity("cronTrigger", "group1")
    //            .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
    //            .build();
    //    }
    //
    //
    //    @EventListener(ContextRefreshedEvent.class)
    //    public void scheduleJobs() throws SchedulerException {
    //        scheduler.addJob(job0(), true);
    //        scheduler.addJob(job1(), true);
    //        scheduler.addJob(job2(), true);
    //        scheduler.scheduleJob(cronTrigger());
    //    }
}
