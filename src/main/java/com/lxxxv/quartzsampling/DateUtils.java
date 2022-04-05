package com.lxxxv.quartzsampling;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils
{
    private static long getNTPTime()
    {
        return LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Date date()
    {
        return new Date(getNTPTime());
    }

    public static long currentTimeMillis()
    {
        return date().getTime();
    }

    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId());
    }

    public static LocalDateTime getLocalDateTimePlusDuration(String timestamp, int duration) {
        return timestampToLocalDateTime(Long.parseLong(timestamp)).plusSeconds(duration);
    }
}
