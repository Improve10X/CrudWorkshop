package com.improve10x.crud.base;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.improve10x.crud.CrudApi;
import com.improve10x.crud.CrudService;

public class BaseActivity extends AppCompatActivity {

    protected CrudService crudService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupApiService();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void log(String message) {
        Log.i(this.getLocalClassName(), message);
    }

    private void setupApiService() {
        CrudApi crudApi = new CrudApi();
        crudService = crudApi.createCrudService();
    }
}


