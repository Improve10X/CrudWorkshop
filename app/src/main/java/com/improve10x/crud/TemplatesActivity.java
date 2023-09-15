package com.improve10x.crud;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.improve10x.crud.base.BaseActivity;
import com.improve10x.crud.template.OnItemActionListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemplatesActivity extends BaseActivity {

    private ActivityTemplatesBinding templatesBinding;
    private ArrayList<Template> templates = new ArrayList<>();
    private TemplatesAdapter templatesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        templatesBinding = ActivityTemplatesBinding.inflate(getLayoutInflater());
        setContentView(templatesBinding.getRoot());
        getSupportActionBar().setTitle("Templates");
        log("OnCreate");
        setupTemplatesRv();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.templates_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, AddTemplateActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        log("OnResume");
        fetchTemplates();
    }

    private void fetchTemplates() {
        showProgressBar();
        Call<List<Template>> call = crudService.fetchTemplates();
        call.enqueue(new Callback<List<Template>>() {
            @Override
            public void onResponse(Call<List<Template>> call, Response<List<Template>> response) {
                hideProgressBar();
                List<Template> templates = response.body();
                templatesAdapter.setTemplates(templates);
            }

            @Override
            public void onFailure(Call<List<Template>> call, Throwable t) {
                hideProgressBar();
                showToast("Failed to fetch the templates");
            }
        });
    }

    private void showProgressBar() {
        templatesBinding.templatesProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        templatesBinding.templatesProgressBar.setVisibility(View.GONE);
    }

    private void setupTemplatesRv() {
        templatesBinding.templatesRv.setLayoutManager(new LinearLayoutManager(this));
        templatesAdapter = new TemplatesAdapter();
        templatesAdapter.setTemplates(templates);
        templatesAdapter.setOnItemActionListener(new OnItemActionListener() {
            @Override
            public void onDelete(String id) {
                deleteTemplate(id);
            }

            @Override
            public void onEdit(Template template) {
                editTemplate(template);
            }
        });
        templatesBinding.templatesRv.setAdapter(templatesAdapter);
    }

    private void deleteTemplate(String id) {
        Call<Void> call = crudService.deleteTemplate(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                showToast("Successfully deleted the template");
                fetchTemplates();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                showToast("Failed to delete the template");
            }
        });
    }

    private void editTemplate(Template template) {
        Intent intent = new Intent(this, EditTemplateActivity.class);
        intent.putExtra(Constants.KEY_TEMPLATE, template);
        startActivity(intent);
    }
}