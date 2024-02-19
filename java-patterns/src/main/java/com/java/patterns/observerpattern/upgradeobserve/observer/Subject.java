package com.java.patterns.observerpattern.upgradeobserve.observer;

/**
 * 数据提供方接口
 *
 * @author yihur
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param o
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者
     *
     * @param o
     */
    void removeObserver(Observer o);

    /**
     * 通知观察者
     */
    void notifyObservers();
}
