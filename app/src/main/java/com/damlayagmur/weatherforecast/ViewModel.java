package com.damlayagmur.weatherforecast;

import android.content.Context;
import android.widget.Toast;

import com.damlayagmur.weatherforecast.Activity.MainActivity;
import com.damlayagmur.weatherforecast.Activity.WeatherForecastAdapter;
import com.damlayagmur.weatherforecast.Model.Model;
import com.damlayagmur.weatherforecast.Model.Recycler;
import com.damlayagmur.weatherforecast.Service.DailyWeatherService;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewModel {


    MainActivity mainActivity;
    Retrofit retrofit;
    WeatherForecastAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public void getWeatherData(String lat, String lon, final RecyclerView rc) {
        retrofit = new Retrofit.Builder().baseUrl(DailyWeatherService.baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        final DailyWeatherService api = retrofit.create(DailyWeatherService.class);
        String BASE_URL = String.format("onecall?lat=%s&lon=%s&exclude=hourly,minutely&appid=c018a5b51ea2d25a4b2cc18fff8872e3", lat, lon);
        Call <Model> call = api.getWeatherData(BASE_URL);
        call.enqueue(new Callback <Model>() {
            @Override
            public void onResponse(Call <Model> call, Response <Model> response) {
                Model dailyWeather = response.body();

                mAdapter = new WeatherForecastAdapter();
                rc.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getContext());
                rc.setLayoutManager(mLayoutManager);
                rc.setAdapter(mAdapter);
                mAdapter.clear();
                mAdapter.add(new Recycler("Current", dailyWeather.currentModel.weatherModels.get(0).getDescription(), dailyWeather.currentModel.getTemp() + " °K", dailyWeather.currentModel.getTemp() + " °K"));
                for (int k = 0; k < dailyWeather.dailyModels.size(); k++) {
                    mAdapter.add(new Recycler("Day " + (k + 1), dailyWeather.dailyModels().get(k).weatherModels.get(0).getDescription(), dailyWeather.dailyModels().get(k).getTempModel().getMax() + " °K", dailyWeather.dailyModels().get(k).getTempModel().getMin() + " °K"));
                }
            }
            @Override
            public void onFailure(Call <Model> call, Throwable t) {
                Toast.makeText(mainActivity, "Ups!! Somethings went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
