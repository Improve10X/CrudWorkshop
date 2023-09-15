package com.improve10x.crud.message;

import com.improve10x.crud.Message;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Message message);
}

