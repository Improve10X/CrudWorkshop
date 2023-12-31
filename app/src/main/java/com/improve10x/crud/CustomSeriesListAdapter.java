package com.improve10x.crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomSeriesListAdapter extends ArrayAdapter<Series> {

    public CustomSeriesListAdapter(@NonNull Context context, int resource, @NonNull List<Series> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_drop_down_item, parent, false);
        Series series = getItem(position);
        TextView titleTxt = view.findViewById(R.id.text_view);
        titleTxt.setText(series.seriesId + "_" + series.title);
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.series_drop_down_item, parent, false);
        Series series = getItem(position);
        TextView titleTxt = view.findViewById(R.id.text_view);
        titleTxt.setText(series.seriesId + "_" + series.title);
        return view;
    }
}
