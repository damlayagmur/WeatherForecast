package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DailyModel {

    @SerializedName("dt")
    public long dt;

    @SerializedName("temp")
    public TempModel tempModel;

    @SerializedName("weather")
    public List <WeatherModel> weatherModels;

    public long getDt() {
        return dt;
    }

    public TempModel getTempModel() {
        return tempModel;
    }

    public List <WeatherModel> getWeatherModels() {
        return weatherModels;
    }
}
