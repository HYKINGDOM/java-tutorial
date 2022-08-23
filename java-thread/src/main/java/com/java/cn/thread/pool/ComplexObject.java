package com.java.cn.thread.pool;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 复杂的对象，创建出来比较耗时间
 */
public class ComplexObject {

    private String name;

    public ComplexObject(String name) {
        try {
            long t1 = System.currentTimeMillis();
            // 模拟创建耗时操作
            ThreadLocalRandom tlr = ThreadLocalRandom.current();
            Thread.sleep(4000 + tlr.nextInt(2000));
            long t2 = System.currentTimeMillis();
            System.out.println(name + " 创建耗时: " + (t2-t1) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
