package com.improve10x.crud.series;

import com.improve10x.crud.Series;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Series series);
}
