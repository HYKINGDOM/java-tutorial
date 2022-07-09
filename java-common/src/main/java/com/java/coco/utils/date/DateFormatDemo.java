package com.java.coco.utils.date;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import cn.hutool.core.date.DateUtil;


/**
 * @author HY
 */
public class DateFormatDemo {


    public static void main(String[] args) throws ParseException {

        System.out.println(LocalDate.now());
        System.out.println(LocalDateTime.now());
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
