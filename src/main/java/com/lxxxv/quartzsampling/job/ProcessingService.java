package com.lxxxv.quartzsampling.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.lxxxv.quartzsampling.Semaphore;

@Slf4j
@Service
public class ProcessingService
{
    private final Semaphore semaphore = Semaphore.getINSTANCE();

    public void execute()
    {
        System.out.println("execute");

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
