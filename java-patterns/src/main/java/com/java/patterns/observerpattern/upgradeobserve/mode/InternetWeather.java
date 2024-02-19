package com.java.patterns.observerpattern.upgradeobserve.mode;

/**
 * 测试方法
 *
 * @author yihur
 */
public class InternetWeather {

    public static void main(String[] args) {

        CurrentConditions mCurrentConditions = new CurrentConditions();
        ForcesConditions mForcesConditions = new ForcesConditions();
        WeatherDataSt mWeatherDataSt = new WeatherDataSt();

        //注册天气看板
        mWeatherDataSt.registerObserver(mCurrentConditions);
        mWeatherDataSt.registerObserver(mForcesConditions);

        mWeatherDataSt.setData(30, 150, 40);
        //移除看板
        mWeatherDataSt.removeObserver(mCurrentConditions);
        mWeatherDataSt.setData(40, 250, 50);
    }

}
