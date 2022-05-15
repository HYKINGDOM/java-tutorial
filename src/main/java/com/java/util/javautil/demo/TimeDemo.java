package com.java.util.javautil.demo;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class TimeDemo {


    public static void main(String[] args) {

        List<Object[]>  list = new ArrayList<>();

        Timestamp time1 = Timestamp.from(Instant.now());
        Timestamp time2 = Timestamp.from(Instant.now());
        Object[] objects = new Object[]{time1,time2};

        list.add(objects);

        for (Object[] objects1 : list) {
            long time = Timestamp.valueOf(objects1[1].toString()).getTime();
            LocalDateTime localDateTime = timestampToDatetime(time);
            System.out.println(localDateTime);
        }


    }



    public static LocalDateTime timestampToDatetime(long timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }




    public static String change(int second){
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second%3600;
        if(second>3600){
            h= second/3600;
            if(temp!=0){
                if(temp>60){
                    d = temp/60;
                    if(temp%60!=0){
                        s = temp%60;
                    }
                }else{
                    s = temp;
                }
            }
        }else{
            d = second/60;
            if(second%60!=0){
                s = second%60;
            }
        }

        return h+"时"+d+"分"+s+"秒";
    }
}
