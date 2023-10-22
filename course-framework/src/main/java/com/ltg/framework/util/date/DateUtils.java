package com.ltg.framework.util.date;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;


/**
 * <p> ClassName: DateUtil </p>
 * <p> Package: com.ltg.framework.util </p>
 * <p> Description: </p>
 * <p></p>
 *
 * @Author: LTG
 * @Create: 2023/2/7 - 22:32
 * @Version: v1.0
 */
public class DateUtils {
    public static final String[] WEEKS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 根据日期获取某个月第一天和最后一天
     *
     * @param date
     * @return
     */
    public static MonthLocalDate getMonthDay(LocalDate date) {
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth()); // 获取当前月的第一天
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth()); // 获取当前月的最后一天
        return new MonthLocalDate(firstDay, lastDay);
    }


    /**
     * 获取两个日期的时间,筛选常用
     * @param startDate
     * @param endDate
     * @return
     */
    public static BetweenTime getBetweenTowDaysTime(String startDate, String endDate) {
        BetweenTime betweenTime = new BetweenTime();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        if (StringUtils.isNotBlank(startDate) || StringUtils.isNotBlank(endDate)) {
            startTime = LocalDateTime.of(LocalDate.parse(startDate), LocalTime.MIN);
            endTime = LocalDateTime.of(LocalDate.parse(endDate), LocalTime.MAX);
        }
        betweenTime.setStartTime(startTime);
        betweenTime.setEndTime(endTime);
        return betweenTime;
    }


}
