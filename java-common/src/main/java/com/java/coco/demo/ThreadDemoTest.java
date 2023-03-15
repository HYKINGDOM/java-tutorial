package com.java.coco.demo;

public class ThreadDemoTest {


    public static void main(String[] args) throws InterruptedException {


        for (int j = 0; j < 10; j++) {
            String strId = "dasdqwdawdawd";

            Thread.sleep(2000);


            int exitCode = 0;
            for (int i = 0; i < 100; i++) {

                exitCode = exitCode + i;

            }

            strId = strId + exitCode;

            System.out.println(strId);
        }


    }

}
