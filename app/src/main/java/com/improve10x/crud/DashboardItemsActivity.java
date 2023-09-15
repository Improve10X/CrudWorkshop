package com.improve10x.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class DashboardItemsActivity extends AppCompatActivity {

    private ActivityDashboardBinding dashboardBinding;
    private ArrayList<DashboardItem> dashboardItems;
    private DashboardItemsAdapter dashboardItemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(dashboardBinding.getRoot());
        getSupportActionBar().setTitle("Dashboard");
        setupData();
        setupDashboardItemsRv();
    }

    private void setupData() {
        dashboardItems = new ArrayList<>();

        DashboardItem messages = new DashboardItem("Messages", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTu0UnVXyY8c_xGtHghOAss3fvf2Y4iI-SaTXgPHXeZV8ztO2PwKtVqhw5Yu0F0cBo3ayY&usqp=CAU");
        dashboardItems.add(messages);

        DashboardItem templates = new DashboardItem("Templates", "https://i.pinimg.com/736x/45/47/48/4547485aa9c80128166352e2fe224b73.jpg");
        dashboardItems.add(templates);

        DashboardItem seriesItems = new DashboardItem("Series", "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/574a12ae-b953-4f51-8253-5a7f34d477fb/dce38iv-c126dcac-1629-4a7b-b410-4eb8546227a7.png/v1/fill/w_512,h_512,q_80,strp/series_general_folder_icon_by_mrartoholic_dce38iv-fullview.jpg");
        dashboardItems.add(seriesItems);

        DashboardItem movies = new DashboardItem("Movies", "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/i/ed4f9bf6-0144-4b2e-bfff-60bd129cd587/d4q8dy8-4bc1e5ef-46f7-42c8-b8dd-0856f232098b.jpg");
        dashboardItems.add(movies);
    }

    private void setupDashboardItemsRv() {
        dashboardBinding.dashboardRv.setLayoutManager(new LinearLayoutManager(this));
        dashboardItemsAdapter = new DashboardItemsAdapter();
        dashboardItemsAdapter.setData(dashboardItems);
        dashboardBinding.dashboardRv.setAdapter(dashboardItemsAdapter);
    }
}