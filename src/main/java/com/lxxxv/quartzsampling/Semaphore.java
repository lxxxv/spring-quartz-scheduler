package com.lxxxv.quartzsampling;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Semaphore
{
    public enum StatusType
    {

        START("start"),
        FINISH("finish");

        private final String value;

        StatusType(String value) {
            this.value = value;
        }

        public String value() {
            return value;
        }
    }

    private static Semaphore INSTANCE;
    private static Map<String, Object> map;

    public static Semaphore getINSTANCE()
    {
        if (null == INSTANCE)
        {
            synchronized (Semaphore.class)
            {
                if (null == INSTANCE)
                {
                    INSTANCE = new Semaphore();
                    map = new ConcurrentHashMap<>();
                }
            }
        }
        return INSTANCE;
    }

    public Map<String, Object> getMap()
    {
        if (null == map) map = new ConcurrentHashMap<>();
        return map;
    }
}
