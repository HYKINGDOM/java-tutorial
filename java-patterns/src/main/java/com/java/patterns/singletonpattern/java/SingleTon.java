package com.java.patterns.singletonpattern.java;

/**
 * 单例模式的经典实现
 * @author Administrator
 */
public class SingleTon {

    private static SingleTon uniqueInstance = null;

    private SingleTon() {

    };

    public static SingleTon getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SingleTon();
        }
        return uniqueInstance;

    }

}
