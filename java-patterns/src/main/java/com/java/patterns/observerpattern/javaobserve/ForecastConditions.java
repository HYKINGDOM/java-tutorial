package com.java.patterns.observerpattern.javaobserve;

import java.util.Observable;
import java.util.Observer;

/**
 * 天气看板
 *
 * @author yihur
 */
public class ForecastConditions implements Observer {

    private float mTemperate;
    private float mPressure;
    private float mHumidity;

    /**
     * 数据更新 同时调用展示数据方法
     *
     * @param arg0
     * @param arg1
     */
    @Override
    public void update(Observable arg0, Object arg1) {
        this.mTemperate = ((AssembleWeatherData)(arg1)).mTemperate;
        this.mPressure = ((AssembleWeatherData)(arg1)).mPressure;
        this.mHumidity = ((AssembleWeatherData)(arg1)).mHumidity;
        display();
    }

    /**
     * 展示数据
     */
    public void display() {
        System.out.println("**明天温度:" + (mTemperate + Math.random()) + "**");
        System.out.println("**明天气压:" + (mPressure + 10 * Math.random()) + "**");
        System.out.println("**明天湿度:" + (mHumidity + Math.random()) + "**");
    }

}
