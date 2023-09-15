package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.movie.OnItemActionListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> movies;
    public OnItemActionListener onItemActionListener;

    public void setData(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener actionListener) {
        onItemActionListener = actionListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MoviesItemBinding binding = MoviesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MovieViewHolder moviesViewHolder = new MovieViewHolder(binding);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = this.movies.get(position);
        holder.binding.movieNameTxt.setText(movie.name);
        if (movie.imageUrl != null && movie.imageUrl.isEmpty() == false) {
            Picasso.get().load(movie.imageUrl).into(holder.binding.movieImg);
        }
        holder.binding.moviesDeleteIb.setOnClickListener(view -> {
            onItemActionListener.onDelete(movie.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(movie);
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
