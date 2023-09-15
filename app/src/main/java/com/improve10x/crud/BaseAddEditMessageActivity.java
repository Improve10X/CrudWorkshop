package com.improve10x.crud;

import android.os.Bundle;
import android.view.Menu;

import com.improve10x.crud.R;
import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.databinding.ActivityAddEditMessageBinding;

public class BaseAddEditMessageActivity extends BaseActivity {

   protected ActivityAddEditMessageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_edit_message_menu, menu);
        return true;
    }
}