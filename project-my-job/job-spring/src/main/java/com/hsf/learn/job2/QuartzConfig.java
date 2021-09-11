package com.hsf.learn.job2;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @Author: qingshan
 * @Date: 2019/9/5 17:19
 * @Description: 咕泡学院，只为更好的你
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail printTimeJobDetail(){
        return JobBuilder.newJob(MyJob1.class)
                .withIdentity("gupaoJob","group")
                .usingJobData("gupao", "职位更好的你")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(printTimeJobDetail())
                .withIdentity("gupaoJob","group")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
    @Bean
    public SchedulerFactoryBean scheduler(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(printTimeJobTrigger());
        return schedulerFactoryBean;
    }
}
