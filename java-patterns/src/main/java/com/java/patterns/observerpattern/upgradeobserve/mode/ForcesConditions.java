package com.java.patterns.observerpattern.upgradeobserve.mode;

import com.java.patterns.observerpattern.upgradeobserve.observer.Observer;

/**
 * 明天天气看板
 *
 * @author yihur
 */
public class ForcesConditions implements Observer {
    private float mTemperate;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(float mTemperate, float mPressure, float mHumidity) {
        this.mTemperate = mTemperate;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        display();
    }

    public void display() {
        System.out.println("**明天温度:" + (mTemperate + Math.random()) + "**");
        System.out.println("**明天气压:" + (mPressure + 10 * Math.random()) + "**");
        System.out.println("**明天湿度:" + (mHumidity + Math.random()) + "**");
    }
}
