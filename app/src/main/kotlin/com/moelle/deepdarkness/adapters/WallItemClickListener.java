package com.moelle.deepdarkness.adapters;

import android.widget.ImageView;

import com.moelle.deepdarkness.models.Wall;

public interface WallItemClickListener {

    void onMovieClick(Wall wall, ImageView movieImageView); // we will need the imageview to make the shared animation between the two activity

}