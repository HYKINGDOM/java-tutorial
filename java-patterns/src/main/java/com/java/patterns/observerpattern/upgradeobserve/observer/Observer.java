package com.java.patterns.observerpattern.upgradeobserve.observer;

/**
 * 观察者 接口
 *
 * @author yihur
 */
public interface Observer {

    /**
     * 数据更新
     *
     * @param mTemperate
     * @param mPressure
     * @param mHumidity
     */
    void update(float mTemperate, float mPressure, float mHumidity);
}
