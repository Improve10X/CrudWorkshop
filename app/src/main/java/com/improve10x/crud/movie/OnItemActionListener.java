package com.improve10x.crud.movie;

import com.improve10x.crud.Movie;

public interface OnItemActionListener {

    void onDelete(String id);

    void onEdit(Movie movie);
}
