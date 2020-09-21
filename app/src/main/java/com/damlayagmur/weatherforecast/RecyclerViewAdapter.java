package com.damlayagmur.weatherforecast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damlayagmur.weatherforecast.Model.RecyclerModel;
import com.damlayagmur.weatherforecast.Model.WeatherModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private String[] nodeDescription;
    private Listener listener;
    private ArrayList<RecyclerModel> weatherModels;


    interface Listener {
        void onClick(int position);
    }

    public RecyclerViewAdapter(ArrayList<RecyclerModel> weatherModels) {

        this.weatherModels = weatherModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cv = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, final int position) {
        RecyclerModel currentItem = weatherModels.get(position);
        holder.day.setText(String.valueOf(currentItem.getDay()));
        holder.description.setText(currentItem.getDescription());
        holder.tempMin.setText(String.valueOf(currentItem.getTempMin()));
        holder.tempMax.setText(String.valueOf(currentItem.getTempMax()));
        //TextView description = cardView.findViewById(R.id.nodetitle_nodeContextFragment_card);
        //description.setText(nodeDescription[position]);
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return weatherModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView day;
        public TextView tempMax;
        public TextView tempMin;
        public ViewHolder(View v) {
            super(v);
            description = v.findViewById(R.id.textView_classrecyclerview_weatherDescription);
            day = v.findViewById(R.id.textView_classrecyclerview_days);
            tempMax=v.findViewById(R.id.textView_classrecyclerview_tempMax);
            tempMin=v.findViewById(R.id.textView_classrecyclerview_tempMin);
        }
    }
}

