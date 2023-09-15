package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddTemplateActivity extends BaseAddEditTemplateActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Template");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String messageText = binding.messagesEditTxt.getText().toString();
            addTemplate(messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addTemplate(String messageText) {
       Template template = new Template(messageText);
        Call<Template> call = crudService.createTemplate(template);
        call.enqueue(new Callback<Template>() {
            @Override
            public void onResponse(Call<Template> call, Response<Template> response) {
                showToast("successfully added the template");
                finish();
            }

            @Override
            public void onFailure(Call<Template> call, Throwable t) {
                showToast("failed to add the template");
            }
        });
    }
}
