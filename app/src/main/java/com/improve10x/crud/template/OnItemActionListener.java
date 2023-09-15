package com.improve10x.crud.template;

import com.improve10x.crud.Template;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Template template);
}
