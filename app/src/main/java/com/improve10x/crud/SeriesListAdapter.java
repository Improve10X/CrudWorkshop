package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.series.OnItemActionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesListAdapter extends RecyclerView.Adapter<SeriesViewHolder> {

    private List<Series> seriesList;
    public OnItemActionListener onItemActionListener;
    public void setSeriesList(List<Series> seriesList) {
        this.seriesList = seriesList;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public SeriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SeriesItemBinding binding = SeriesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
          SeriesViewHolder seriesViewHolder = new SeriesViewHolder(binding);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SeriesViewHolder holder, int position) {
        Series series = seriesList.get(position);
        holder.binding.seriesNameTxt.setText(series.title);
        if (series.imageUrl != null && series.imageUrl.isEmpty() == false) {
            Picasso.get().load(series.imageUrl).into(holder.binding.seriesImg);
        }
        holder.binding.seriesDeleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(series.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(series);
        });
    }

    @Override
    public int getItemCount() {
        return seriesList.size();
    }
}
