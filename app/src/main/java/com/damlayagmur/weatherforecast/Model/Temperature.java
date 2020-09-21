package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("min")
    private double min;

    @SerializedName("max")
    private double max;

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
