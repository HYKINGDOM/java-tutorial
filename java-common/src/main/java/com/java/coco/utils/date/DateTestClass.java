package com.java.coco.utils.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTestClass {

    public static void main(String[] args) {

        DateTestClass dateTestClass = new DateTestClass();
        dateTestClass.localDateToLocalDateTime();
    }

    public void localDateToLocalDateTime() {
        LocalDate now = LocalDate.now();
        LocalDate localDate = now.plusDays(2L);

        System.out.println(now);
        System.out.println(localDate);

        System.out.println(now.atStartOfDay());
        System.out.println(localDate.atTime(LocalTime.MIN));
        System.out.println(localDate.atTime(LocalTime.MAX));
    }

    public void localToLocalDateTime() {
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
