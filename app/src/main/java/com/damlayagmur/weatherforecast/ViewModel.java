package com.damlayagmur.weatherforecast;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.damlayagmur.weatherforecast.Activity.MainActivity;
import com.damlayagmur.weatherforecast.Activity.WeatherForecastAdapter;
import com.damlayagmur.weatherforecast.Model.Model;
import com.damlayagmur.weatherforecast.Model.Recycler;
import com.damlayagmur.weatherforecast.Service.DailyWeatherService;
import com.google.android.gms.maps.GoogleMap;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewModel  {


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



   /* @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        // geolocate();

        if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
            // mGoogleMap.setOnMarkerClickListener(this);
        }
        init(editText_Search,getContext());
    }*/

    /*public void init(final EditText v, final Context context) {
        v.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionID, KeyEvent keyEvent) {
                if (actionID == EditorInfo.IME_ACTION_SEARCH || actionID == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == keyEvent.ACTION_DOWN || keyEvent.getAction() == keyEvent.KEYCODE_ENTER) {

                    //mainActivity.geolocate();
                    return true;
                }
                return false;
            }
        });
    }*/

    /*public void geolocate(EditText v, Context context) {
        String searchString = v.getText().toString();
        Geocoder geocoder = new Geocoder(context);
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

            Toast.makeText(mainActivity, searchString + "'s Latitude  " + Lat.toString() + "  " + searchString + "'s Longtitude  " + Long.toString(), Toast.LENGTH_SHORT).show();
            String searchLat = Lat.toString();
            String searchLong = Long.toString();
            mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(latLng, 20)));
            //getWeatherData(searchLat,searchLong);
        }
    }*/

    public void getWeatherData(String lat, String lon, final RecyclerView rc) {
        retrofit = new Retrofit.Builder().baseUrl(DailyWeatherService.baseURL).addConverterFactory(GsonConverterFactory.create()).build();
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
               // System.out.println("Sunrise:" + dailyWeather.getCurrentModel().getSunrise());
                for (int i = 0; i < dailyWeather.currentModel.getWeatherModels().size(); i++) {

                    System.out.println("Description:" + dailyWeather.getCurrentModel().weatherModels.get(i).getDescription());
                }
                for (int j = 0; j < dailyWeather.dailyModels().size(); j++) {
                    System.out.println("Dt:" + dailyWeather.dailyModels().get(j).getDt());
                }
                mAdapter = new WeatherForecastAdapter();
                rc.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(getContext());
                rc.setLayoutManager(mLayoutManager);
                rc.setAdapter(mAdapter);
                mAdapter.clear();
                mAdapter.add(new Recycler("Current",dailyWeather.currentModel.weatherModels.get(0).getDescription(),dailyWeather.currentModel.getTemp()+" °K",dailyWeather.currentModel.getTemp() + " °K"));
                for (int k = 0; k < dailyWeather.dailyModels.size(); k++) {
                    System.out.println("TempMin:" + dailyWeather.dailyModels().get(k).getTempModel().getMin());
                    mAdapter.add(new Recycler("Day "+(k+1), dailyWeather.dailyModels().get(k).weatherModels.get(0).getDescription(), dailyWeather.dailyModels().get(k).getTempModel().getMax() + " °K", dailyWeather.dailyModels().get(k).getTempModel().getMin() + " °K"));
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
                Toast.makeText(mainActivity, "Ups!! Somethings went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }


}
