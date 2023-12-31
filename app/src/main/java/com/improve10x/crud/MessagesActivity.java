package com.improve10x.crud;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.message.OnItemActionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessagesActivity extends BaseActivity {

    private ActivityMessagesBinding messagesBinding;
    private ArrayList<Message> messages = new ArrayList<>();
    private MessagesAdapter messagesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        messagesBinding = ActivityMessagesBinding.inflate(getLayoutInflater());
        setContentView(messagesBinding.getRoot());
        getSupportActionBar().setTitle("Messages");
        log("OnCreate");
        setupMessagesRv();
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("OnResume");
        fetchMessages();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messages_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddMessageActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fetchMessages() {
        showProgressBar();
        Call<List<Message>> call = crudService.fetchMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                hideProgressBar();
                List<Message> messages = response.body();
                messagesAdapter.setData(messages);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to load Messages");
            }
        });
    }

    private void showProgressBar() {
        messagesBinding.progressbar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        messagesBinding.progressbar.setVisibility(View.GONE);
    }

    private void setupMessagesRv() {
        messagesBinding.messagesRv.setLayoutManager(new LinearLayoutManager(this));
        messagesAdapter = new MessagesAdapter();
        messagesAdapter.setData(messages);
        messagesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteMessage(id);
            }

            @Override
            public void onEdit(Message message) {
                editMessage(message);
            }
        });
        messagesBinding.messagesRv.setAdapter(messagesAdapter);
    }

    private void deleteMessage(String id) {
        Call<Void> call = crudService.deleteMessage(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("successfully deleted the message");
                fetchMessages();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("failed to delete the message");
            }
        });
    }

    private void editMessage(Message message) {
        Intent intent = new Intent(this, EditMessageActivity.class);
        intent.putExtra(Constants.KEY_MESSAGE, message);
        startActivity(intent);
    }
}