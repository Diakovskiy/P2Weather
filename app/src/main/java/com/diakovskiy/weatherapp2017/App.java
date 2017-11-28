package com.diakovskiy.weatherapp2017;

import  android.app.Application;

public class App extends Application{

    private SettingsManager settingsManager;

    @Override
    public void onCreate() {
        super.onCreate();
        settingsManager = new SettingsManager(this);
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}