package com.java.tutorial.project.common.kits;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 *
 * 日期工具类
 *
 */
public final class DateKit {

    /**
     * 年月日时分秒
     */
    public static final DateTimeFormatter TFT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 年月日
     */
    public static final DateTimeFormatter TF = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * LocalDateTime转时间戳
     */
    public static long getTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 时间戳转LocalDateTime
     */
    public static LocalDateTime fromTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, OffsetDateTime.now().getOffset());
    }

    /**
     * LocalDateTime转Date
     */
    public static Date toDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate转Date
     */
    public static Date toDate(LocalDate date) {
        return toDate(date.atTime(LocalTime.now(ZoneId.systemDefault())));
    }

    /**
     * Date转LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 日期格式化
     */
    public static String formatTime(LocalDateTime time, String patten) {
        return DateTimeFormatter.ofPattern(patten).format(time);
    }

    /**
     * 日期格式化
     */
    public static String formatTime(LocalDateTime time, DateTimeFormatter formatter) {
        return formatter.format(time);
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     */
    public static String time2tft(LocalDateTime time) {
        return TFT.format(time);
    }

    /**
     * 日期格式化 yyyy-MM-dd
     */
    public String time2tf(LocalDateTime time) {
        return TF.format(time);
    }

    /**
     * 日期格式化 yyyy-MM-dd
     */
    public String date2tf(LocalDate date) {
        return TF.format(date);
    }

    /**
     * 字符串转LocalDateTime
     */
    public static LocalDateTime parseTime(String time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(formatter.parse(time));
    }

    /**
     * 字符串转LocalDateTime
     */
    public static LocalDateTime parseTime(String time) {
        return LocalDateTime.from(TFT.parse(time));
    }

    /**
     * 字符串转LocalDate
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.from(TF.parse(date));
    }

}
