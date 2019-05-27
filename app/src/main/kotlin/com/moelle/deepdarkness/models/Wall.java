package com.moelle.deepdarkness.models;

public class Wall {

    private String title;
    private int thumbnail;
    private int coverPhoto;


    public Wall(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
        this.coverPhoto = thumbnail;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}
