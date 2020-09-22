package com.java.util.javautil.threadread;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class SingleThread {

    // 共享变量
    public static String content = "空";

    public static void main(String[] args) {

        // 线程1 - 写入数据
        new Thread(() -> {
            try {
                while (true) {
                    Files.write(Paths.get("test.log"), Collections.singleton(content = "当前时间" + System.currentTimeMillis()));
                    Thread.sleep(1000L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // 线程2 - 读取数据
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(1000L);
                    byte[] allBytes = Files.readAllBytes(Paths.get("test.log"));
                    System.out.println("读取日志:" + new String(allBytes));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
