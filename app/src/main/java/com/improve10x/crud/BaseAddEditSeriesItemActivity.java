package com.improve10x.crud;

import android.os.Bundle;
import android.view.Menu;

import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;


public class BaseAddEditSeriesItemActivity extends BaseActivity {

    protected ActivityAddEditSeriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditSeriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_series_menu, menu);
        return true;
    }
}