package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMovieActivity extends BaseAddEditMovieActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MOVIE)) {
            getSupportActionBar().setTitle("Edit Movie");
            movie = (Movie) getIntent().getSerializableExtra(Constants.KEY_MOVIE);
            showData();
        }
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
            updateMovie(movie.id, seriesId, movieId, name, imageUrl, description);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMovie(String id, String seriesId, String movieId, String name, String imageUrl, String description) {
        movie = new Movie(seriesId, movieId, name, imageUrl, description);
        Call<Void> call = crudService.updateMovie(id, movie);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the movie");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to update the movie");
            }
        });
    }
}
