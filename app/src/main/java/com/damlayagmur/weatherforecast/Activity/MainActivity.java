package com.damlayagmur.weatherforecast.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.damlayagmur.weatherforecast.Model.Model;
import com.damlayagmur.weatherforecast.Model.Recycler;
import com.damlayagmur.weatherforecast.R;
import com.damlayagmur.weatherforecast.Service.DailyWeatherService;
import com.damlayagmur.weatherforecast.ViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback  {
    CardView logout;
    private GoogleMap mGoogleMap;
    private FusedLocationProviderClient googleApiClient;
    private static final int request_user_location_code = 101;
    private EditText editText_Search;
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    WeatherForecastAdapter mAdapter;
    ViewModel viewModel = new ViewModel();

    ListView listView;
    // private static final String BASE_URL = "onecall?lat=+" +"&lon=26.889199&exclude=hourly,minutely&appid=c018a5b51ea2d25a4b2cc18fff8872e3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);
        logout = findViewById(R.id.cardView_activityMain_logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutf(view);
            }
        });
        editText_Search = findViewById(R.id.editText_mapfragment);
        recyclerView = findViewById(R.id.recyclerView);



        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        // weatherModels.add(new WeatherModel("dddddddd","wwwwww"));


        googleApiClient = LocationServices.getFusedLocationProviderClient(this);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, request_user_location_code);
        }
        //Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(DailyWeatherService.baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        init();
        //getWeatherData();

        /*recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mAdapter = new WeatherForecastAdapter();
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);*/
        //viewModel.geolocate(editText_Search,this);

    }

    /*public void getWeatherData(String lat, String lon) {
        final DailyWeatherService api = retrofit.create(DailyWeatherService.class);
        String BASE_URL = String.format("onecall?lat=%s&lon=%s&exclude=hourly,minutely&appid=c018a5b51ea2d25a4b2cc18fff8872e3", lat, lon);
        Call <Model> call = api.getWeatherData(BASE_URL);
        call.enqueue(new Callback <Model>() {
            @Override
            public void onResponse(Call <Model> call, Response <Model> response) {
                Model dailyWeather = response.body();

                System.out.println("Timezone:" + dailyWeather.getTimezone());
                System.out.println("Lat:" + dailyWeather.getLat());
                System.out.println("Lon:" + dailyWeather.getLon());
                System.out.println("Timezone Offset:" + dailyWeather.getTimezone_offset());
                System.out.println("Sunrise:" + dailyWeather.getCurrentModel().getSunrise());
                for (int i = 0; i < dailyWeather.currentModel.getWeatherModels().size(); i++) {

                    System.out.println("Description:" + dailyWeather.getCurrentModel().weatherModels.get(i).getDescription());
                }
                for (int j = 0; j < dailyWeather.dailyModels().size(); j++) {
                    System.out.println("Dt:" + dailyWeather.dailyModels().get(j).getDt());
                }
                mAdapter.clear();
                for (int k = 0; k < dailyWeather.dailyModels.size(); k++) {
                    System.out.println("TempMin:" + dailyWeather.dailyModels().get(k).getTempModel().getMin());
                    mAdapter.add(new Recycler(k, dailyWeather.dailyModels().get(k).weatherModels.get(0).getDescription(), dailyWeather.dailyModels().get(k).getTempModel().getMin() + "°K", dailyWeather.dailyModels().get(k).getTempModel().getMax() + "°K"));
                    System.out.println("Weather Description:" + dailyWeather.dailyModels().get(k).weatherModels.get(0).getDescription());
                }



                //  ArrayList<String>arrayList = new ArrayList <>();
                //arrayList.add(dailyWeather.getTimezone());
                //arrayList.add(((String) dailyWeather.getLon()));

                //ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,arrayList);
                //listView.setAdapter(arrayAdapter);
                // System.out.println(dailyWeather.getCurrentModel().getWeather().get(1));

                //for(WeatherModel data : weather){
                //System.out.println(api.getLat1());

                //}
                //System.out.println("xxxxxxxxx");
                //Log.d("lat",data.getLat());
                //}
            }

            @Override
            public void onFailure(Call <Model> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ups!! Somethings went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }*/

    private void getCurrentLocation() {
        Task <Location> task = googleApiClient.getLastLocation();
        final Context cv;
        cv = this;
        task.addOnSuccessListener(new OnSuccessListener <Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null) {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                            MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Current Location");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                            googleMap.addMarker(markerOptions);

                        }
                    });
                }
                viewModel.getWeatherData(String.valueOf(location.getLatitude()), String.valueOf(location.getLongitude()),recyclerView);
            }
        });

        init();
    }

    public void geolocate() {
        String searchString = editText_Search.getText().toString();
        Geocoder geocoder = new Geocoder(this);
        List <Address> addressList = new ArrayList <>();
        try {
            addressList = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e) {
            Log.e(TAG, "getLocate :IOException" + e.getMessage());
        }
        if (addressList.size() > 0) {
            Address address = addressList.get(0);
            Double Lat = address.getLatitude();
            Double Long = address.getLongitude();
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Search Location");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

            mGoogleMap.addMarker(markerOptions);

            Toast.makeText(this, searchString + "'s Latitude  " + Lat.toString() + "  " + searchString + "'s Longtitude  " + Long.toString(), Toast.LENGTH_SHORT).show();
            String searchLat = Lat.toString();
            String searchLong = Long.toString();
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, 20)));
            viewModel.getWeatherData(searchLat,searchLong,recyclerView);
        }
    }

    private void init() {
        editText_Search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH || actionID == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == keyEvent.ACTION_DOWN || keyEvent.getAction() == keyEvent.KEYCODE_ENTER) {

                    geolocate();
                    return true;
                }
                return false;
            }
        });
    }

    public void logoutf(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        // geolocate();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
            // mGoogleMap.setOnMarkerClickListener(this);
        }
        init();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case request_user_location_code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //getCurrentLocation();
                }
                return;
        }
    }

    @Override
    public void onBackPressed() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }


}
