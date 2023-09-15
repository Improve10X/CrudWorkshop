package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditTemplateActivity extends BaseAddEditTemplateActivity {

    private Template template;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_TEMPLATE)) {
            getSupportActionBar().setTitle("Edit Template");
            template = (Template) getIntent().getSerializableExtra(Constants.KEY_TEMPLATE);
            showData();
        }
    }

    private void showData() {
        binding.messagesEditTxt.setText(template.messageText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String messageText = binding.messagesEditTxt.getText().toString();
            updateTemplate(template.id, messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateTemplate(String id, String messageText) {
        Template template = new Template(messageText);
        Call<Void> call = crudService.updateTemplate(id, template);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully edited the template");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to edit the template");
            }
        });
    }
}
