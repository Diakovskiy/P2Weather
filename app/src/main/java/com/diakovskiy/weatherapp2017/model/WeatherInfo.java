package com.diakovskiy.weatherapp2017.model;

/**
 * Created by d.diakovskiy on 15.11.2017.
 */

public class WeatherInfo {
    private String temperatureDescription;
    private String humidityDescription;
    public WeatherInfo(String temperatureDescription, String humidityDescription){

        this.temperatureDescription = temperatureDescription;
        this.humidityDescription    = humidityDescription;

    }

    public String getTemperatureDescription(){
        return temperatureDescription;
    }

    public String getHumidityDescription(){
        return humidityDescription;
    }
}
