package com.diakovskiy.weatherapp2017.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.diakovskiy.weatherapp2017.model.WeatherInfo;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.util.List;

/**
 * Created by d.diakovskiy on 15.11.2017.
 */

public class WeatherService {

    final static String TAG = "Weather:WeatherService";
    final static String GetCurrentWeatherURL = "http://api.openweathermap.org/data/2.5/weather?id=703448&APPID=84fd2d3ec162fc1b50aa820de98168c8&units=metric&lang=ru";

    public void updateWeather(CurrentWeatherListener currentWeatherListener){
        new AsyncTaskUpdateCurrentWeather().execute(currentWeatherListener, null, null);
    }

    //connects to site openweathermap.org and get json with current wheather into json format
    //return null, if meets some error
    //
    private String getCurrentWhetherJson(){

        String jsonFromWeatherSite = null;
        //fake unswer jsonFromWeatherSite ="{\"coord\":{\"lon\":30.52,\"lat\":50.43},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"broken clouds\",\"icon\":\"04d\"}],\"base\":\"stations\",\"main\":{\"temp\":4.68,\"pressure\":1028,\"humidity\":80,\"temp_min\":4,\"temp_max\":5},\"visibility\":10000,\"wind\":{\"speed\":3,\"deg\":270},\"clouds\":{\"all\":75},\"dt\":1510822800,\"sys\":{\"type\":1,\"id\":7358,\"message\":0.0031,\"country\":\"UA\",\"sunrise\":1510809267,\"sunset\":1510841431},\"id\":703448,\"name\":\"Kiev\",\"cod\":200}";

        try {
            Log.d(TAG, "getCurrentWhetherJson: trying to visit "+ GetCurrentWeatherURL);
            OkHttpClient client = new OkHttpClient();
            Request request     = new Request.Builder().url(GetCurrentWeatherURL).build();
            Log.d(TAG, "getCurrentWeather: request "+request.toString());
            Response response = client.newCall(request).execute();
            Log.d(TAG, "getCurrentWeather: response "+response.toString());
            jsonFromWeatherSite =  response.body().string();
            Log.d(TAG, "getCurrentWeather: final json " + jsonFromWeatherSite);

        }   catch (Exception exp){
            exp.printStackTrace();
        }
        return jsonFromWeatherSite;
    }

    private WeatherInfo convertJsonToWeatherInfo(String jsonFromWeatherSite){

        if (jsonFromWeatherSite == null) {
            return new WeatherInfo("connection error...", "connection error...");
        } else {

            Gson gson = new Gson();
            CurrentWeatherPojo currentWeatherPojo = gson.fromJson(jsonFromWeatherSite, CurrentWeatherPojo.class);
            Log.d(TAG, "getCurrentWeather: "+currentWeatherPojo.toString());

            return new WeatherInfo("temperature " + currentWeatherPojo.main.temp.toString(),"humidity " + currentWeatherPojo.main.humidity.toString());
        }

    }

    public class AsyncTaskUpdateCurrentWeather extends AsyncTask <CurrentWeatherListener , Void, WeatherInfo> {
        private CurrentWeatherListener currentWeatherListener = null;

        protected WeatherInfo doInBackground(CurrentWeatherListener ... currentWeatherListener) {
            this.currentWeatherListener = currentWeatherListener[0];

            String jsonFromSite = getCurrentWhetherJson();
            WeatherInfo weatherInfo = convertJsonToWeatherInfo(jsonFromSite);
            return weatherInfo;

        }

        protected void onPostExecute(WeatherInfo weatherInfo) {
            currentWeatherListener.onWeatherUpdate(weatherInfo);
        }

    }


    class CurrentWeatherPojo {

        public Coord coord;
        public List<Weather> weather = null;
        public String base;
        public Main main;
        public Integer visibility;
        public Wind wind;
        public Clouds clouds;
        public Integer dt;
        public Sys sys;
        public Integer id;
        public String name;
        public Integer cod;

        public class Sys {

            public Integer type;
            public Integer id;
            public Float message;
            public String country;
            public Integer sunrise;
            public Integer sunset;

        }
        class Main {

            public Float temp;
            public Integer pressure;
            public Integer humidity;
            public Integer tempMin;
            public Integer tempMax;

        }
        class Coord {

            public Float lon;
            public Float lat;

        }
        class Weather {

            public Integer id;
            public String main;
            public String description;
            public String icon;

        }
        class Wind {

            public Integer speed;
            public Integer deg;

        }

        class Clouds {
            public Integer all;
        }


    }



}
