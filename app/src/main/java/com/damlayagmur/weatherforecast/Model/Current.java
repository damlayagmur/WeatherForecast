package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Current {

    @SerializedName("dt")
    private int dt;

    @SerializedName("sunrise")
    private long sunrise;

    @SerializedName("sunset")
    private int sunset;

    @SerializedName("weather")
    public List<WeatherModel> weatherModels;

    public int getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }

    public List <WeatherModel> getWeatherModels() {
        return weatherModels;
    }
}
