package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Movie");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String name = binding.movieNameTxt.getText().toString();
            String movieId = binding.movieIdTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            String description = binding.descriptionTxt.getText().toString();
            Series series = (Series) binding.seriesListSp.getSelectedItem();
            String seriesId = series.seriesId;
            addMovie(seriesId, movieId, name, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMovie(String seriesId, String movieId, String name, String imageUrl, String description) {
        Movie movie = new Movie(seriesId, movieId, name,  imageUrl, description);
        Call<Movie> call = crudService.createMovie(movie);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                showToast("Successfully added movie");
                finish();
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                showToast("Failed to add movie");
            }
        });
    }
}
