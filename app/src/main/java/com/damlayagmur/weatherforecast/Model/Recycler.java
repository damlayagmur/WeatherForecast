package com.damlayagmur.weatherforecast.Model;

public class Recycler {

    public String day;
    public String description;
    public String tempMax;
    public String tempMin;


    public Recycler(String day, String description, String tempMax, String tempMin) {
        this.day = day;
        this.description = description;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public String getDay() {
        return day;
    }

    public String getDescription() {
        return description;
    }

    public String getTempMax() {
        return tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }
}
