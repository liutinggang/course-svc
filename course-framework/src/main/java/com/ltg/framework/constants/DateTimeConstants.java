package com.ltg.framework.constants;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.TimeZone;

/**
 * nekoimi  2021/12/6 11:44
 *
 * 时间相关
 */
public interface DateTimeConstants {
    String DEFAULT_ZONE = "Asia/Shanghai";
    String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATE_TIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_FORMAT, Locale.CHINA);
    DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT, Locale.CHINA);
    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT, Locale.CHINA);
    TimeZone DEFAULT_TIME_ZONE = TimeZone.getTimeZone(ZoneId.of(DEFAULT_ZONE));
    ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();
}
