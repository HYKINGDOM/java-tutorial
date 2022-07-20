package com.java.patterns.observerpattern.javaobserve;

/**
 * 气象站返回组装数据类
 * @author yihur
 */
public class AssembleWeatherData {
    public float mTemperate;
    public float mPressure;
    public float mHumidity;

    public AssembleWeatherData(float mTemperate, float mPressure, float mHumidity) {
        this.mTemperate = mTemperate;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
    }
}
