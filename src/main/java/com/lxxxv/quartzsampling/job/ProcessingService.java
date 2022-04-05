package com.lxxxv.quartzsampling.job;

import lombok.extern.slf4j.Slf4j;
import com.lxxxv.quartzsampling.Semaphore;

@Slf4j
public class ProcessingService
{
    private final Semaphore semaphore = Semaphore.getINSTANCE();

    public void execute()
    {
        System.out.println("execute : " + this);

        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException e)
        {
            log.error("thread sleep error : ", e);
        }
    }
}
