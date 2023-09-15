package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditSeriesActivity extends BaseAddEditSeriesItemActivity {

    private Series series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_SERIES)) {
            getSupportActionBar().setTitle("Edit Series");
            series = (Series) getIntent().getSerializableExtra(Constants.KEY_SERIES);
            showData();
        }
    }

    private void showData() {
        binding.seriesNameEditTxt.setText(series.title);
        binding.imageUrlTxt.setText(series.imageUrl);
        binding.seriesEditIdTxt.setText(series.seriesId);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = binding.seriesEditIdTxt.getText().toString();
            String title = binding.seriesNameEditTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            updateSeriesItem(series.id, seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateSeriesItem(String id, String seriesId, String title, String imageUrl) {
        Series series = new Series(seriesId, title, imageUrl);
        Call<Void> call = crudService.updateSeriesItem(id, series);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the series");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to update the series");
            }
        });
    }
}
