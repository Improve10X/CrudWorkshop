package com.improve10x.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.SeriesItemBinding;

public class SeriesViewHolder extends RecyclerView.ViewHolder {

    SeriesItemBinding binding;

    public SeriesViewHolder(SeriesItemBinding seriesItemBinding) {
        super(seriesItemBinding.getRoot());
        binding = seriesItemBinding;
    }
}