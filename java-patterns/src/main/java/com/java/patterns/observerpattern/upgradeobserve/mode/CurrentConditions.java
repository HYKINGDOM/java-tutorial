package com.java.patterns.observerpattern.upgradeobserve.mode;


import com.java.patterns.observerpattern.upgradeobserve.observer.Observer;

/**
 * 当前看板
 *
 * @author yihur
 */
public class CurrentConditions implements Observer {

    private float mTemperate;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(float mTemperate, float mPressure, float mHumidity) {
        this.mHumidity = mHumidity;
        this.mPressure = mPressure;
        this.mTemperate = mTemperate;
        display();
    }

    public void display() {
        System.out.println("***Today mTemperate:" + mTemperate + "***");
        System.out.println("***Today mPressure:" + mPressure + "***");
        System.out.println("***Today mHumidity:" + mHumidity + "***");

    }

}