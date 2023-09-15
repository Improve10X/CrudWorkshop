package com.improve10x.crud;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.movie.OnItemActionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends BaseActivity {

    private ActivityMoviesBinding moviesBinding;
    private ArrayList<Movie> movies = new ArrayList<>();
    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesBinding = ActivityMoviesBinding.inflate(getLayoutInflater());
        setContentView(moviesBinding.getRoot());
        getSupportActionBar().setTitle("Movies");
        log("OnCreate");
        setupMoviesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("OnResume");
        fetchMovies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMovieActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMovies() {
        showProgressBar();
        Call<List<Movie>> call = crudService.fetchMovies();
        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                hideProgressBar();
                List<Movie> movies = response.body();
                moviesAdapter.setData(movies);
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                hideProgressBar();
                showToast("failed to fetch Movies");
            }
        });
    }

    private void showProgressBar() {
        moviesBinding.moviesProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        moviesBinding.moviesProgressBar.setVisibility(View.GONE);
    }

    private void setupMoviesRv() {
        moviesBinding.moviesRv.setLayoutManager(new GridLayoutManager(this, 2));
        moviesAdapter = new MoviesAdapter();
        moviesAdapter.setData(movies);
        moviesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMovie(id);
            }

            @Override
            public void onEdit(Movie movie) {
                editMovie(movie);
            }
        });
        moviesBinding.moviesRv.setAdapter(moviesAdapter);
    }


    private void deleteMovie(String id) {
        Call<Void> call = crudService.deleteMovie(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted the movie");
                fetchMovies();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete the movie");
            }
        });
    }

    private void editMovie(Movie movie) {
        Intent intent = new Intent(this, EditMovieActivity.class);
        intent.putExtra(Constants.KEY_MOVIE, movie);
        startActivity(intent);
    }
}