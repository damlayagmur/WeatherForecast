package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {

    @SerializedName("timezone")
    public String timezone;

    @SerializedName("lat")
    public double  lat;

    @SerializedName("lon")
    public String lon;

    @SerializedName("timezone_offset")
    public int timezone_offset;

    @SerializedName("current")
    public CurrentModel currentModel;

    public CurrentModel getCurrentModel() {
        return currentModel;
    }

    @SerializedName("daily")
    public List<DailyModel> dailyModels;

    public List <DailyModel> dailyModels() {
        return dailyModels;
    }

    public String getTimezone() {
        return timezone;
    }

    public double getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }
}
