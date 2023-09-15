package com.improve10x.crud;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.series.OnItemActionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesItemsActivity extends BaseActivity {

    private ActivitySeriesBinding seriesBinding;
    private ArrayList<Series> seriesItems = new ArrayList<>();
    private SeriesListAdapter seriesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seriesBinding = ActivitySeriesBinding.inflate(getLayoutInflater());
        setContentView(seriesBinding.getRoot());
        log("OnCreate");
        getSupportActionBar().setTitle("Series");
        setupSeriesItemsRv();
        setupApiService();
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("OnResume");
        fetchSeriesItems();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.series_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddSeriesItemActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deleteSeriesItem(String id) {
        Call<Void> call = crudService.deleteSeriesItem(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted the series");
                fetchSeriesItems();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete the series");
            }
        });
    }

    private void fetchSeriesItems() {
        showProgressBar();
        Call<List<Series>> call = crudService.fetchSeriesItems();
        call.enqueue(new Callback<List<Series>>() {
            @Override
            public void onResponse(Call<List<Series>> call, Response<List<Series>> response) {
                hideProgressBar();
                List<Series> series = response.body();
                seriesListAdapter.setSeriesList(series);
            }

            @Override
            public void onFailure(Call<List<Series>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to fetch series");
            }
        });
    }

    private void showProgressBar() {
        seriesBinding.seriesProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        seriesBinding.seriesProgressBar.setVisibility(View.GONE);
    }

    private void setupSeriesItemsRv() {
        seriesBinding.seriesRv.setLayoutManager(new LinearLayoutManager(this));
        seriesListAdapter = new SeriesListAdapter();
        seriesListAdapter.setSeriesList(seriesItems);
        seriesListAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteSeriesItem(id);
            }

            @Override
            public void onEdit(Series series) {
                editSeriesItem(series);
            }
        });
        seriesBinding.seriesRv.setAdapter(seriesListAdapter);
    }

    private void editSeriesItem(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        intent.putExtra(Constants.KEY_SERIES, series);
        startActivity(intent);
    }
}