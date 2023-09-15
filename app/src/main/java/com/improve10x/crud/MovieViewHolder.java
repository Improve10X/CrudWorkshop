package com.improve10x.crud;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.MoviesItemBinding;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    MoviesItemBinding binding;

    public MovieViewHolder(MoviesItemBinding moviesItemBinding) {
        super(moviesItemBinding.getRoot());
        binding = moviesItemBinding;
         }
}
