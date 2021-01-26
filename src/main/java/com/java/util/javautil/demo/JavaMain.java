package com.java.util.javautil.demo;


import java.time.LocalDate;

import static com.java.util.javautil.demo.JavaStream.testDemo;

public class JavaMain {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = localDate.minusDays(16);
        System.out.println(localDate1);
        LocalDate localDate2 = localDate.minusDays(14);
        System.out.println(localDate2);
        LocalDate localDate3 = localDate.minusDays(1316);
        System.out.println(localDate3);
    }
}
