package com.damlayagmur.weatherforecast.Model;

public class Recycler {

    private String day;
    private String description;
    private String tempMax;
    private String tempMin;


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
