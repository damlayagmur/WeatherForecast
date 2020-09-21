package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("lat")
    private double  lat;

    @SerializedName("lon")
    private String lon;

    @SerializedName("timezone_offset")
    private int timezone_offset;

    @SerializedName("current")
    public Current currentModel;

    public Current getCurrentModel() {
        return currentModel;
    }

    @SerializedName("daily")
    public List<Daily> dailyModels;

    public List <Daily> dailyModels() {
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
