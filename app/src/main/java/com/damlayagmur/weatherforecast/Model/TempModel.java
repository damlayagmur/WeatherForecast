package com.damlayagmur.weatherforecast.Model;

import com.google.gson.annotations.SerializedName;

public class TempModel {

    @SerializedName("min")
    public double  min;

    @SerializedName("max")
    public double  max;

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
