package com.lxxxv.quartzsampling.job;

import java.util.Date;
import java.util.Properties;
import java.time.ZoneId;
import java.io.IOException;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.lxxxv.quartzsampling.DateUtils;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProcessingController
{
    public static final Date START_UP_DATETIME = Date.from
    (
        DateUtils.getLocalDateTimePlusDuration
        (
            String.valueOf(DateUtils.currentTimeMillis()), 5
        ).atZone(ZoneId.systemDefault()).toInstant()
    );

    @PostConstruct
    public void constructor()
    {
        try
        {
            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory(this.getScheduleProperties());
            Scheduler scheduler = stdSchedulerFactory.getScheduler();

            scheduler.start();

            JobDetail jobDetail = buildJobDetail(ProcessingJob.class, ProcessingJob.class.getSimpleName());
            if (scheduler.checkExists(jobDetail.getKey())) scheduler.deleteJob(jobDetail.getKey());
            scheduler.scheduleJob(jobDetail, buildCronJobTrigger(1));
        }
        catch (SchedulerException e)
        {
            log.error("An error occurred while during quartz", e);
        }
    }

    public Properties getScheduleProperties()
    {
        Properties props = new Properties();
        try
        {
            props.load(getClass().getResourceAsStream("/org/quartz/quartz.properties"));
        }
        catch(IOException e)
        {
            log.error("an error make properties : ", e);
        }
        return props;
    }

    private JobDetail buildJobDetail(Class job, String name)
    {
        return JobBuilder
                .newJob(job)
                .withIdentity(name)
                .build();
    }

    private Trigger buildCronJobTrigger(Integer seconds)
    {
        return TriggerBuilder.newTrigger()
                .startAt(START_UP_DATETIME)
                .withSchedule
                (
                    SimpleScheduleBuilder
                        .simpleSchedule()
                        .repeatForever()
                        .withIntervalInSeconds(seconds)
                )
                .build();
    }
}
