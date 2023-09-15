package com.improve10x.crud;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemsViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;

    void setData(ArrayList<DashboardItem> dashboardItems) {
        this.dashboardItems = dashboardItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DashboardItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DashboardItemBinding binding = DashboardItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        DashboardItemsViewHolder dashboardItemsViewHolder = new DashboardItemsViewHolder(binding);
        return dashboardItemsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardItemsViewHolder holder, int position) {
        DashboardItem dashboardItem = this.dashboardItems.get(position);
        holder.binding.nameTxt.setText(dashboardItem.title);
        Picasso.get().load(dashboardItem.imageUrl).into(holder.binding.imageViewImg);
        holder.itemView.setOnClickListener(view -> {
            if (holder.binding.nameTxt.getText().toString().equalsIgnoreCase("Messages")) {
                Intent intent = new Intent(holder.itemView.getContext(), MessagesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.nameTxt.getText().toString().equalsIgnoreCase("Templates")) {
                Intent intent = new Intent(holder.binding.imageViewImg.getContext(), TemplatesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.nameTxt.getText().toString().equalsIgnoreCase("Series")) {
                Intent intent = new Intent(holder.binding.imageViewImg.getContext(), SeriesItemsActivity.class);
                holder.itemView.getContext().startActivity(intent);
            } else if (holder.binding.nameTxt.getText().toString().equalsIgnoreCase("Movies")) {
                Intent intent = new Intent(holder.binding.imageViewImg.getContext(), MoviesActivity.class);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }
}
