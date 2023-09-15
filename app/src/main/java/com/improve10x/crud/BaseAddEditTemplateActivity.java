package com.improve10x.crud;

import android.os.Bundle;
import android.view.Menu;

import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;

public class BaseAddEditTemplateActivity extends BaseActivity {

    protected ActivityAddEditTemplateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditTemplateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        log("OnCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }
}