package com.java.util.javautil.patterns.observerpattern.baseobserve;

/**
 * 天气数据
 *
 * @author yihur
 */
public class WeatherData {

    /**
     * 温度
     */
    private float mTemperate;

    /**
     * 压力
     */
    private float mPressure;

    /**
     * 湿度
     */
    private float mHumidity;

    private CurrentConditions mCurrentConditions;

    /**
     * 加入需要通知的看板
     * @param mCurrentConditions
     */
    public WeatherData(CurrentConditions mCurrentConditions) {
        this.mCurrentConditions = mCurrentConditions;
    }

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
     * 通知数据修改
     */
    public void dataChange() {
        mCurrentConditions.update(getTemperature(), getPressure(), getHumidity());
    }

    /**
     * 数据修改 同时调用通知方法
     *
     * @param mTemperature
     * @param mPressure
     * @param mHumidity
     */
    public void setData(float mTemperature, float mPressure, float mHumidity) {
        this.mTemperate = mTemperature;
        this.mPressure = mPressure;
        this.mHumidity = mHumidity;
        dataChange();
    }

}
