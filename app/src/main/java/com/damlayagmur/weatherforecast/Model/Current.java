package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Current {


    @SerializedName("temp")
    private double temp;


    @SerializedName("weather")
    public List<WeatherModel> weatherModels;



    public double getTemp() {
        return temp;
    }

    public List <WeatherModel> getWeatherModels() {
        return weatherModels;
    }
}
