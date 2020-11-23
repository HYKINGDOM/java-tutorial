package com.java.util.javautil.patterns.observerpattern.javaobserve;


/**
 *
 * 使用java内置观察者实现观察者模式
 * @author yihur
 */
public class InternetWeather {

    public static void main(String[] args) {
        CurrentConditions mCurrentConditions;
        ForecastConditions mForecastConditions;
        WeatherData mWeatherData;

        mCurrentConditions = new CurrentConditions();
        mForecastConditions = new ForecastConditions();
        mWeatherData = new WeatherData();

        //java内置观察者模式中的addObserver方法，用于注册观察者
        mWeatherData.addObserver(mCurrentConditions);
        mWeatherData.addObserver(mForecastConditions);
        mWeatherData.setData(30, 150, 40);

        //内置方法，用于移除观察者，重新修改数据
        mWeatherData.deleteObserver(mCurrentConditions);
        mWeatherData.setData(35, 150, 60);

    }
}
