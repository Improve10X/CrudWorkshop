package com.improve10x.crud;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMessageActivity extends BaseAddEditMessageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Message");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.save) {
            String name = binding.nameTxt.getText().toString();
            String phoneNumber = binding.phoneNumberTxt.getText().toString();
            String messageText = binding.messageTextTxt.getText().toString();
            addMessage(name, phoneNumber, messageText);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void addMessage(String name, String phoneNumber, String messageText) {
        Message message = new Message(name, phoneNumber, messageText);
        Call<Message> call = crudService.createMessage(message);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                showToast("Successfully added message");
                finish();
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                showToast("failed to add message");
            }
        });
    }
}
