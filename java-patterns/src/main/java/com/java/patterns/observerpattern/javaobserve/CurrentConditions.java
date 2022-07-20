package com.java.patterns.observerpattern.javaobserve;

import java.util.Observable;
import java.util.Observer;


/**
 * 当前观察者
 * @author yihur
 */
public class CurrentConditions implements Observer {

    private float mTemperate;
    private float mPressure;
    private float mHumidity;

    @Override
    public void update(Observable arg0, Object arg1) {
        this.mTemperate = ((AssembleWeatherData) (arg1)).mTemperate;
        this.mPressure = ((AssembleWeatherData) (arg1)).mPressure;
        this.mHumidity = ((AssembleWeatherData) (arg1)).mHumidity;
        display();
    }

    public void display() {
        System.out.println("***Today mTemperate:" + mTemperate + "***");
        System.out.println("***Today mPressure:" + mPressure + "***");
        System.out.println("***Today mHumidity:" + mHumidity + "***");
    }


}
