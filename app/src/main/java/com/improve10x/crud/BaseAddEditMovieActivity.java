package com.improve10x.crud;

import android.os.Bundle;
import android.view.Menu;

import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityAddEditMoviesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseAddEditMovieActivity extends BaseActivity {

    protected CustomSeriesListAdapter customSeriesListAdapter;
    private ArrayList<Series> seriesList = new ArrayList<>();
    protected Movie movie;
    protected ActivityAddEditMoviesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditMoviesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupSeriesListSp();
        fetchSeriesList();
    }

    private void fetchSeriesList() {
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                List<Series> series = response.body();
                customSeriesListAdapter.addAll(series);
                if (movie != null) {
                    showData();
                }
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                showToast("failed to fetch series");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_movie_menu, menu);
        return true;
    }

    private void setupSeriesListSp() {
        customSeriesListAdapter = new CustomSeriesListAdapter(this, android.R.layout.simple_list_item_1, seriesList);
        binding.seriesListSp.setAdapter(customSeriesListAdapter);
    }

    protected void showData() {
        binding.movieNameTxt.setText(movie.name);
        binding.movieIdTxt.setText(movie.movieId);
        binding.imageUrlTxt.setText(movie.imageUrl);
        binding.descriptionTxt.setText(movie.description);
        for (int i = 0; i < customSeriesListAdapter.getCount(); i++) {
            Series series = customSeriesListAdapter.getItem(i);
            if (movie.seriesId.equals(series.seriesId)) {
                binding.seriesListSp.setSelection(i);
            }
        }
    }
}