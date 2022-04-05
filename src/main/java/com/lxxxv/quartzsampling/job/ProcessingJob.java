package com.lxxxv.quartzsampling.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
@RequiredArgsConstructor
public class ProcessingJob extends QuartzJobBean implements InterruptableJob
{
    private final ProcessingService processingService;
    private boolean isInterrupted = false;
    private JobKey jobKey = null;

    @Override
    public void interrupt() throws UnableToInterruptJobException
    {
        log.info(jobKey + " " + "INTERRUPTED");
        isInterrupted = true;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException
    {
        if (isInterrupted)
        {
            log.warn("JobKey: " + jobKey + " is interrupted.");
            return;
        }

        processingService.execute();
    }
}
