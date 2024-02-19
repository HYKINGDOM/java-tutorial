package com.java.tutorial.project.config;

/**
 * @author HY
 */
public class TestInheritableThreadLocal implements Runnable {
    private static final InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    @Override
    public void run() {
        System.out.println("----子线程设置值前获取：" + threadLocal.get());
        System.out.println("----新开线程设置值为\"子线程\"");
        threadLocal.set("子线程");
        System.out.println("----新开的线程设置值后获取：" + threadLocal.get());
    }
}