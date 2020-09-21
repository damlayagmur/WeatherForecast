package com.damlayagmur.weatherforecast.Model;

public class Recycler {

    public int day;
    public String description;
    public String tempMax;
    public String tempMin;


    public Recycler(int day, String description, String tempMax, String  tempMin) {
        this.day = day;
        this.description = description;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
    }

    public int getDay() {
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
