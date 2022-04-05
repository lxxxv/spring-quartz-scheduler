package com.lxxxv.quartzsampling.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lxxxv.quartzsampling.Semaphore;
import com.lxxxv.quartzsampling.Semaphore.StatusType;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
public class ProcessingJob extends QuartzJobBean implements InterruptableJob
{
    private final ProcessingService processingService;
    private final Semaphore semaphore = Semaphore.getINSTANCE();
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

        if (null == semaphore.getMap().get("STATUS") || StatusType.FINISH == semaphore.getMap().get("STATUS"))
        {
            semaphore.getMap().put("STATUS", StatusType.START);
            processingService.execute();
            semaphore.getMap().put("STATUS", StatusType.FINISH);
        }
    }
}
