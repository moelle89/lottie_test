package com.moelle.deepdarkness.models;

public class Wall {

    private String title;
    private String thumbnailUrl;

    public Wall(String title, String thumbnailUrl) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getCoverPhotoUrl() {
        return thumbnailUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}