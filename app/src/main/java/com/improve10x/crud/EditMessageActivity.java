package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditMessageActivity extends BaseAddEditMessageActivity {

    private Message message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra(Constants.KEY_MESSAGE)) {
            getSupportActionBar().setTitle("Edit Messages");
            message = (Message) getIntent().getSerializableExtra(Constants.KEY_MESSAGE);
            showData();
        }
    }

    private void showData() {
        binding.nameTxt.setText(message.name);
        binding.phoneNumberTxt.setText(message.phoneNumber);
        binding.messageTextTxt.setText(message.messageText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String name = binding.nameTxt.getText().toString();
            String phoneNumber = binding.phoneNumberTxt.getText().toString();
            String messageText = binding.messageTextTxt.getText().toString();
            updateMessage(message.id, name, phoneNumber, messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateMessage(String id, String name, String phoneNumber, String messageText) {
        Message message = new Message(name, phoneNumber, messageText);
        Call<Void> call = crudService.updateMessage(id, message);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully updated the  message");
                finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to update the message");
            }
        });
    }
}

