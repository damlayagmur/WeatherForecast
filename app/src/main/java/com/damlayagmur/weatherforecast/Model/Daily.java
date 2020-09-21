package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {

    @SerializedName("dt")
    private long dt;

    @SerializedName("temp")
    private Temperature tempModel;

    @SerializedName("weather")
    public List <WeatherModel> weatherModels;

    public long getDt() {
        return dt;
    }

    public Temperature getTempModel() {
        return tempModel;
    }

    public List <WeatherModel> getWeatherModels() {
        return weatherModels;
    }
}
