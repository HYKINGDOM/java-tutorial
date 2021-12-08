package com.java.util.javautil.date;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author HY
 */
public class DateFormatDemo {


    public static void main(String[] args) throws ParseException {


    }


    private void sqlDateConvertToUtilDate() {
        String str = "2021-04-05";
        Date newDate = java.sql.Date.valueOf(str);
    }


    public void dateFormatLocal() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2019-01-01 00:00:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, dateTimeFormatter);
        System.out.println(localDateTime);
    }
}
