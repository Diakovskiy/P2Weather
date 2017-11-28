package com.diakovskiy.weatherapp2017.utils;

import com.diakovskiy.weatherapp2017.model.WeatherInfo;

/**
 * Created by d.diakovskiy on 16.11.2017.
 */

public interface CurrentWeatherListener {
    void onWeatherUpdate(WeatherInfo weatherInfo);
}
