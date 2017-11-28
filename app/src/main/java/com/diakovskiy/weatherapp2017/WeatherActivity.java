package com.diakovskiy.weatherapp2017;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.diakovskiy.weatherapp2017.databinding.ActivityWeatherBinding;
import com.diakovskiy.weatherapp2017.model.WeatherInfo;
import com.diakovskiy.weatherapp2017.utils.WeatherService;
import com.diakovskiy.weatherapp2017.viewmodel.WeatherInfoViewModel;

public class WeatherActivity extends AppCompatActivity {

    static String TAG = "WEATHER_ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        WeatherService weatherService = new WeatherService();
        WeatherInfoViewModel weatherInfoViewModel = new WeatherInfoViewModel(this, weatherService);
        ActivityWeatherBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_weather);
        binding.setViewModel(weatherInfoViewModel);

    }
}
