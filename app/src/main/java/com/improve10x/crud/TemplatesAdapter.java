package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.template.OnItemActionListener;

import java.util.List;

public class TemplatesAdapter extends RecyclerView.Adapter<TemplateViewHolder> {

    private List<Template> templates;
    public OnItemActionListener onItemActionListener;

    public void setTemplates(List<Template> templates) {
        this.templates = templates;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListener) {
        this.onItemActionListener = onItemActionListener;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TemplatesItemBinding binding = TemplatesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        TemplateViewHolder templateViewHolder = new TemplateViewHolder(binding);
        return templateViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder holder, int position) {
        Template template = templates.get(position);
        holder.binding.templatesTxt.setText(template.messageText);
        holder.binding.deleteBtn.setOnClickListener(view -> {
            onItemActionListener.onDelete(template.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(template);
        });
    }

    @Override
    public int getItemCount() {
        return templates.size();
    }
}
