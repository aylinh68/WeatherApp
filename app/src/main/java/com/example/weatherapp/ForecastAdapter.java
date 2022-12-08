package com.example.weatherapp;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Aylin Hosseinchi
 *
 * This is the adapter class that takes care of loading data onto the recycler view
 */

public class ForecastAdapter extends RecyclerView.Adapter {

    private ArrayList<String> mDays;
    private ArrayList<String> mTemperature;
    private ArrayList<Drawable> mIcon;
    private ArrayList<String> mDescription;

    public ForecastAdapter(ArrayList<String> days, ArrayList<String> temperature, ArrayList<Drawable> icon, ArrayList<String> description) {

        mDays = days;
        mTemperature = temperature;
        mIcon = icon;
        mDescription = description;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_item, parent, false);
        ForecastAdapterViewHolder forecastAdapterViewHolder = new ForecastAdapterViewHolder(view);
        return forecastAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ForecastAdapterViewHolder) holder).bindView(position);
    }

    @Override
    public int getItemCount() {
        return mDays.size();
    }

    public class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView day;
        private TextView temp;
        private ImageView icon;
        private TextView desc;

        public ForecastAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.forecast_day);
            temp = itemView.findViewById(R.id.forecast_temp_value);
            icon = itemView.findViewById(R.id.forecast_temp_image);
            desc = itemView.findViewById(R.id.forecast_temp_description);
        }

        public void bindView(int position) {
            day.setText(mDays.get(position));
            temp.setText(mTemperature.get(position));
            icon.setBackground(mIcon.get(position));
            desc.setText(mDescription.get(position));
        }
    }
}
