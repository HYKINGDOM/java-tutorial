package com.java.util.javautil.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * java 8 时间处理类
 * <p>
 * Instant：瞬时实例。
 * LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
 * LocalTime：本地时间，不包含日期。
 * LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
 * ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 * 新API还引入了 ZoneOffSet 和 ZoneId 类，使得解决时区问题更为简便。解析、格式化时间的 DateTimeFormatter 类也全部重新设计。
 *
 * @author hy852
 */
public class DateUtils {


    /**
     * 获取今天的日期
     */
    public void getCurrentDate() {
        LocalDate today = LocalDate.now();
        System.out.println("Today's Local date : " + today);

        //这个是作为对比
        Date date = new Date();
        System.out.println(date);
    }


    /**
     * 获取年、月、日信息
     */
    public void getDetailDate() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();

        System.out.printf("Year : %d  Month : %d  day : %d t %n", year, month, day);
    }

    /**
     * 处理特定日期
     */
    public void handleSpecilDate() {
        LocalDate dateOfBirth = LocalDate.of(2018, 1, 21);
        System.out.println("The specil date is : " + dateOfBirth);
    }

    /**
     * 判断两个日期是否相等
     */
    public void compareDate() {
        LocalDate today = LocalDate.now();
        LocalDate date1 = LocalDate.of(2018, 01, 21);

        if (date1.equals(today)) {
            System.out.printf("TODAY %s and DATE1 %s are same date %n", today, date1);
        }
    }

    /**
     * 处理周期性的日期
     */
    public void cycleDate() {
        LocalDate today = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(2018, 01, 21);

        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(today);

        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }
    }


    /**
     * 获取当前时间
     */
    public void getCurrentTime() {
        LocalTime time = LocalTime.now();
        System.out.println("local time now : " + time);
    }


    /**
     * 如何计算一周后的日期
     */
    public void nextWeek() {
        LocalDate today = LocalDate.now();
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Today is : " + today);
        System.out.println("Date after 1 week : " + nextWeek);
    }


    /**
     * 计算一年前或一年后的日期
     */
    public void minusDate() {
        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
        System.out.println("Date before 1 year : " + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        System.out.println("Date after 1 year : " + nextYear);
    }


    public void clock() {
        // 根据系统时间返回当前时间并设置为UTC。
        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);

        // 根据系统时钟区域返回时间
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);
    }


    /**
     * 判断日期是早于还是晚于另一个日期
     */
    public void isBeforeOrIsAfter() {
        LocalDate today = LocalDate.now();

        LocalDate tomorrow = LocalDate.of(2018, 1, 29);
        if (tomorrow.isAfter(today)) {
            System.out.println("Tomorrow comes after today");
        }

        //减去一天
        LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);

        if (yesterday.isBefore(today)) {
            System.out.println("Yesterday is day before today");
        }
    }


    /**
     * 获取特定时区下面的时间
     */
    public void getZoneTime() {
        //设置时区
        ZoneId america = ZoneId.of("America/New_York");

        LocalDateTime localtDateAndTime = LocalDateTime.now();

        ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
        System.out.println("现在的日期和时间在特定的时区 : " + dateAndTimeInNewYork);
    }


    /**
     * 使用 YearMonth类处理特定的日期
     */
    public void checkCardExpiry() {
        YearMonth currentYearMonth = YearMonth.now();
        System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());

        YearMonth creditCardExpiry = YearMonth.of(2028, Month.FEBRUARY);
        System.out.printf("Your credit card expires on %s %n", creditCardExpiry);
    }


    /**
     * 检查闰年
     */
    public void isLeapYear() {
        LocalDate today = LocalDate.now();
        if (today.isLeapYear()) {
            System.out.println("This year is Leap year");
        } else {
            System.out.println("2018 is not a Leap year");
        }
    }

    /**
     * 计算两个日期之间的天数和月数
     */
    public void calcDateDays() {
        LocalDate today = LocalDate.now();

        LocalDate java8Release = LocalDate.of(2018, Month.MAY, 14);

        Period periodToNextJavaRelease = Period.between(today, java8Release);

        System.out.println("Months left between today and Java 8 release : "
                + periodToNextJavaRelease.getMonths());
    }

    /**
     * 包含时差信息的日期和时间
     */
    public void zoneOffset() {
        LocalDateTime datetime = LocalDateTime.of(2018, Month.FEBRUARY, 14, 19, 30);
        ZoneOffset offset = ZoneOffset.of("+05:30");
        OffsetDateTime date = OffsetDateTime.of(datetime, offset);
        System.out.println("Date and Time with timezone offset in Java : " + date);
    }

    /**
     * 获取当前的时间戳
     */
    public void getTimestamp(){
        Instant timestamp = Instant.now();
        System.out.println("What is value of this instant " + timestamp);
    }


    /**
     * 使用预定义的格式化工具去解析或格式化日期
     */
    public void formateDate(){
        String dayAfterTommorrow = "20180210";
        LocalDate formatted = LocalDate.parse(dayAfterTommorrow, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.printf("Date generated from String %s is %s %n", dayAfterTommorrow, formatted);
    }

}
