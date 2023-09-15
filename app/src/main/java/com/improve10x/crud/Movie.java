package com.improve10x.crud;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String imageUrl;
    public String movieId;
    public String description;
    public String seriesId;

    public Movie() {
    }

    public Movie(String seriesId, String movieId,String name, String imageUrl, String description) {
        this.seriesId = seriesId;
        this.movieId = movieId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;

    }
}
