package com.moelle.deepdarkness;

import android.app.WallpaperManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

public class WallpaperDetail extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_wallpaper_detail);
        // ini views
        iniViews();

    }

    void iniViews() {
        play_fab = findViewById(R.id.play_fab);
        String imgURL = getIntent().getExtras().getString("imgURL");
        String imgCover = getIntent().getExtras().getString("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_wall_img);
        //Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        Glide.with(this).load(imgURL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(MovieThumbnailImg);
        MovieCoverImg = findViewById(R.id.detail_wall_cover);
        Glide.with(this).load(imgCover).into(MovieCoverImg);
        // setup animation
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation anim_cover = AnimationUtils.loadAnimation(this, R.anim.cover_anim);
        anim_fab.setStartOffset(180);
        anim_fab.setInterpolator(new OvershootInterpolator(3.0f));
        anim_cover.setInterpolator(new FastOutSlowInInterpolator());
        anim_cover.setStartOffset(120);
        MovieCoverImg.setAnimation(anim_cover);
        play_fab.setAnimation(anim_fab);
        //MovieThumbnailImg.setAnimation(anim_thumb);
        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());
                try {
                    myWallpaperManager.setResource(R.raw.w7);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

}
