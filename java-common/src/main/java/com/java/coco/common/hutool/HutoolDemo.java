package com.java.coco.common.hutool;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class HutoolDemo {

    public static void main(String[] args) {
        TimeInterval timer = DateUtil.timer();
        DateTime date = DateUtil.date();
        System.out.println(date);

        String str = "2020-01-01";

        DateTime parse = DateUtil.parse(str);

        System.out.println(parse);

        int year = date.year();
        int month = date.month();
        int day = date.getDay();

        DateTime dateTime = DateUtil.beginOfDay(date);
        DateTime dateTime1 = DateUtil.endOfDay(date);

        System.out.println(dateTime);
        System.out.println(dateTime1);

        DateTime dateTime2 = DateUtil.beginOfHour(date);
        DateTime dateTime3 = DateUtil.endOfHour(date);
        System.out.println(dateTime2);
        System.out.println(dateTime3);

        long interval = timer.interval();
        long l = timer.intervalDay();
        System.out.println(interval);
        System.out.println(l);

        int value = Month.FEBRUARY.getValue();
        System.out.println(value);

        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.FEBRUARY.getValue(), 24);

        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);

        System.out.println(zodiac);
        System.out.println(chineseZodiac);


        //年龄
        int ageOfNow = DateUtil.ageOfNow("1990-01-30");
        //是否闰年
        boolean leapYear = DateUtil.isLeapYear(2017);

        System.out.println(ageOfNow);

        System.out.println(leapYear);


//        Integer[] a = {1,2,3,4,5,6};
//        Integer[] filter = ArrayUtil.filter(a, new Editor<Integer>(){
//            @Override
//            public Integer edit(Integer t) {
//                return (t % 2 == 0) ? t : null;
//            }});
//
//


        String decode = QrCodeUtil.decode(FileUtil.file("img.png"));
        System.out.println(decode);


    }
}
