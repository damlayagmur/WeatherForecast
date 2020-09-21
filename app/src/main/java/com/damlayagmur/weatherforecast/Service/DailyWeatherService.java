package com.damlayagmur.weatherforecast.Service;

import com.damlayagmur.weatherforecast.Model.Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;


public interface DailyWeatherService {

    String baseURL ="https://api.openweathermap.org/data/2.5/";

    @GET()
    Call <Model> getWeatherData(@Url String url);

   // @GET
    //Call <Model> getLat(@Url String url);





}
