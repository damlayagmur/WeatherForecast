package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

public class WeatherModel {

    @SerializedName("main")
    private String main;

    @SerializedName("description")
    private String description;

    public WeatherModel(String main, String description) {
        this.main = main;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
