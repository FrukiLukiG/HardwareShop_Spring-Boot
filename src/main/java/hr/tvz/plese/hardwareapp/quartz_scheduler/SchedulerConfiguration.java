package hr.tvz.plese.hardwareapp.quartz_scheduler;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfiguration {

    @Bean
    public JobDetail hardwareJobDetail(){
        return JobBuilder
                .newJob(HardwareJob.class)
                .withIdentity("hardwareJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger hardwareJobTrigger(){
        return TriggerBuilder
                .newTrigger()
                .forJob(hardwareJobDetail())
                .withIdentity("hardwareTrigger")
                .withSchedule(scheduleBuilder())
                .build();
    }

    @Bean
    public Trigger hardwareJobTriggerLab(){
        return TriggerBuilder
                .newTrigger()
                .forJob(hardwareJobDetail())
                .withIdentity("hardwareTriggerLab")
                .withSchedule(cronScheduleBuilder())
                .build();
    }

    public SimpleScheduleBuilder scheduleBuilder(){
        return SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
    }

    public CronScheduleBuilder cronScheduleBuilder(){
        return CronScheduleBuilder
                .cronSchedule("0 0 0 ? * SAT,SUN");
    }

}