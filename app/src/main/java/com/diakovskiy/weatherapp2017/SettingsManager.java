package com.diakovskiy.weatherapp2017;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsManager {

    private static final String MAIN_PREFERENCES_KEY = "main preferences key1";

    private Context context;
    private SharedPreferences sp;

    public SettingsManager(Context context) {
        this.context = context;
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void storeSomeData(String str){
        SharedPreferences.Editor e = sp.edit();
        e.putString(MAIN_PREFERENCES_KEY, str);
        e.apply();
    }

    public String getSomeData(){
        return sp.getString(MAIN_PREFERENCES_KEY, "");
    }
}
