package com.diakovskiy.weatherapp2017.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.diakovskiy.weatherapp2017.BR;
import com.diakovskiy.weatherapp2017.HistoryActivity;
import com.diakovskiy.weatherapp2017.R;
import com.diakovskiy.weatherapp2017.SettingsActivity;
import com.diakovskiy.weatherapp2017.model.WeatherInfo;
import com.diakovskiy.weatherapp2017.utils.CurrentWeatherListener;
import com.diakovskiy.weatherapp2017.utils.WeatherService;

/**
 * Created by d.diakovskiy on 15.11.2017.
 */

public class WeatherInfoViewModel extends BaseObservable {

    static String TAG = "Weather:WeatherInfoViewModel";
    private Context context;
    private WeatherService weatherService;
    private WeatherInfo weatherInfo = new WeatherInfo("20 C", "it is raining");

    public WeatherInfoViewModel(Context context, WeatherService weatherService){
        this.context = context;
        this.weatherService = weatherService;
    }

    @Bindable
    public String getTemperatureDescription(){
        return weatherInfo.getTemperatureDescription();
    }

    @Bindable
    public String getHumidityDescription(){
        return weatherInfo.getHumidityDescription();
    }

    public void onRefreshWeather(){
        Log.d(TAG, "OnRefreshWeather: starting refreshing weather");

        weatherService.updateWeather(new CurrentWeatherListener() {
            @Override
            public void onWeatherUpdate(WeatherInfo _weatherInfo1) {
                weatherInfo = _weatherInfo1;
                notifyPropertyChanged(BR.temperatureDescription);
                notifyPropertyChanged(BR.humidityDescription);
                Log.d(TAG, "OnRefreshWeather: end refreshing weather " + weatherInfo.getTemperatureDescription()+" " +weatherInfo.getHumidityDescription());
            }
        });

    }

    public void onShowHistory(){

        Intent intent = new Intent(context, HistoryActivity.class);
        context.startActivity(intent);
    }

    public void onShowSettings(){
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

}
