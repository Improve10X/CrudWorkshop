package com.improve10x.crud;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.R;
import com.improve10x.crud.databinding.DashboardItemBinding;

public class DashboardItemsViewHolder extends RecyclerView.ViewHolder {

    DashboardItemBinding binding;

    public DashboardItemsViewHolder(DashboardItemBinding dashboardItemBinding) {
        super(dashboardItemBinding.getRoot());
        binding = dashboardItemBinding;
    }
}
