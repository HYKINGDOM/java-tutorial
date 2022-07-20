package com.java.patterns.observerpattern.javaobserve;

import java.util.Observable;

/**
 * 气象站
 *
 * @author yihur
 */
public class WeatherData extends Observable {
    private float mTemperate;
    private float mPressure;
    private float mHumidity;

    public float getTemperature() {
        return mTemperate;

    }

    public float getPressure() {
        return mPressure;

    }

    public float getHumidity() {
        return mHumidity;

    }

    /**
     * 通知数据已修改
     */
    public void dataChange() {
        //Observable的源码中setChanged()，设置changed为true表示数据已经修改，只有当changed为true的时候notifyObservers()方法才会通知修改
        this.setChanged();
        System.out.println("当前观察者数量：" + countObservers());
        this.notifyObservers(new AssembleWeatherData(getTemperature(), getPressure(), getHumidity()));

    }

    /**
     * 修改数据
     *
     * @param mTemperate
     * @param mPressure
     * @param mHumidity
     */
    public void setData(float mTemperate, float mPressure, float mHumidity) {
        this.mTemperate = mTemperate;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        dataChange();
    }

}
