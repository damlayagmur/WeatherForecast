package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;

public class CurrentModel {

    @SerializedName("dt")
    public int dt;

    @SerializedName("sunrise")
    public long sunrise;

    @SerializedName("sunset")
    public int sunset;

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
