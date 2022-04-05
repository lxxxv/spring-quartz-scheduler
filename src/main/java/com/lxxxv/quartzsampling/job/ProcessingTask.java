package com.lxxxv.quartzsampling.job;

import lombok.extern.slf4j.Slf4j;
import com.lxxxv.quartzsampling.Semaphore;

@Slf4j
public class ProcessingTask
{
    private final Semaphore semaphore = Semaphore.getINSTANCE();

    public ProcessingTask()
    {
        System.out.println("creator : " + Thread.currentThread().getName() + " " + "create : " + this);
    }

    public void execute()
    {
        System.out.println("execute : " + this);

        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            log.error("thread sleep error : ", e);
        }
    }
}
