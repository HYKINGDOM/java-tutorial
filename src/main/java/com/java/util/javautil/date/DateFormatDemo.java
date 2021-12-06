package com.java.util.javautil.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author HY
 */
public class DateFormatDemo {


    public static void main(String[] args) {
        DateFormatDemo dateFormatDemo = new DateFormatDemo();
        dateFormatDemo.dateFormatLocal();
    }

    public void dateFormatLocal(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateStr = "2019-01-01 00:00:00";
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr,dateTimeFormatter);
        System.out.println(localDateTime);
    }
}
