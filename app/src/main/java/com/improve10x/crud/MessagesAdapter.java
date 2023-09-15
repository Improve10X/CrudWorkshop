package com.improve10x.crud;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.improve10x.crud.message.OnItemActionListener;

import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messages;
    public OnItemActionListener onItemActionListener;

    public void setData(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void setOnItemActionListener(OnItemActionListener onItemActionListeners) {
        onItemActionListener = onItemActionListeners;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MessagesItemBinding binding = MessagesItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        MessageViewHolder messagesViewHolder = new MessageViewHolder(binding);
        return messagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.binding.nameTxt.setText(message.name);
        holder.binding.phoneNumberTxt.setText(message.phoneNumber);
        holder.binding.messageTextTxt.setText(message.messageText);
        holder.binding.deleteIb.setOnClickListener(view -> {
            onItemActionListener.onDelete(message.id);
        });
        holder.binding.getRoot().setOnClickListener(view -> {
            onItemActionListener.onEdit(message);
        });
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
