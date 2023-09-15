package com.improve10x.crud;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Series implements Serializable {

    @SerializedName("_id")
    public String id;

    public String title;
    public String imageUrl;
    public String seriesId;

    public Series() {
    }

    public Series(String seriesId, String title, String imageUrl) {
        this.seriesId = seriesId;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}

