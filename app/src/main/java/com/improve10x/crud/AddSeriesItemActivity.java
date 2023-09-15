package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddSeriesItemActivity extends BaseAddEditSeriesItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Series");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String seriesId = this.binding.seriesEditIdTxt.getText().toString();
            String title = binding.seriesNameEditTxt.getText().toString();
            String imageUrl = binding.imageUrlTxt.getText().toString();
            addSeriesItem(seriesId, title, imageUrl);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addSeriesItem(String seriesId, String title, String imageUrl) {
        Series series = new Series(seriesId, title, imageUrl);
        Call<Series> call = crudService.createSeriesItem(series);
        call.enqueue(new Callback<Series>() {
            @Override
            public void onResponse(Call<Series> call, Response<Series> response) {
                showToast("Successfully added the Series");
                finish();
            }

            @Override
            public void onFailure(Call<Series> call, Throwable t) {
                showToast("Failed to add Series");
            }
        });
    }
}
