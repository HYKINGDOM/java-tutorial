package com.java.util.javautil.date;

import java.time.LocalDateTime;

public class DateTestClass {

    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();

        LocalDateTime localDateTime1 = localDateTime.plusDays(1L);
        LocalDateTime localDateTime2 = localDateTime.minusDays(1L);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);
        System.out.println(localDateTime2);

        System.out.println(localDateTime2.isBefore(localDateTime));
        System.out.println(localDateTime1.isAfter(localDateTime));

    }
}
