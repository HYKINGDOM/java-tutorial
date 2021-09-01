package com.java.util.javautil.date;

import java.util.Calendar;
import java.util.Date;

public class NumConvertTime {

    public static void main(String[] args) {
        System.out.println(doubleToDate(0.66666666666666667));
    }


    public static Date doubleToDate(Double dateTime) {

        Calendar base = Calendar.getInstance();

        //Delphi的日期类型从1899-12-30 开始

        base.set(1899, 11, 30, 0, 0, 0);

        base.add(Calendar.DATE, dateTime.intValue());

        base.add(Calendar.MILLISECOND, (int) ((dateTime % 1) * 24 * 60 * 60 * 1000));

        return base.getTime();

    }

}
