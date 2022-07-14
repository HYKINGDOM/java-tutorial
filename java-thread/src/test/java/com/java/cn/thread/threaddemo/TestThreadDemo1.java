package com.java.cn.thread.threaddemo;


/**
 * @author yihur
 * @description
 * @date 2019/4/28
 */
public class TestThreadDemo1 {

    public static void main(String[] args) {
        Thread welcomeThread = new WelcomeThread();
        welcomeThread.start();
        System.out.println("1.welcome:"+Thread.currentThread().getName());
    }




    static class WelcomeThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("2.welcome:"+Thread.currentThread().getName());
        }
    }
}
