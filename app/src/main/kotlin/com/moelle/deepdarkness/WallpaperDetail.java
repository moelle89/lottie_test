package com.moelle.deepdarkness;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static android.graphics.Color.TRANSPARENT;

public class WallpaperDetail extends AppCompatActivity {

    private ImageView MovieThumbnailImg,MovieCoverImg;
    private FloatingActionButton play_fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie_detail);
        // ini views
        iniViews();

    }

    void iniViews() {
        play_fab = findViewById(R.id.play_fab);
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_wall_img);
        //Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        Glide.with(this).load(imageResourceId)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(MovieThumbnailImg);
        MovieCoverImg = findViewById(R.id.detail_wall_cover);
        Glide.with(this).load(imagecover).into(MovieCoverImg);
        // setup animation
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation anim_cover = AnimationUtils.loadAnimation(this, R.anim.cover_anim);
        anim_fab.setStartOffset(100);
        anim_fab.setInterpolator(new OvershootInterpolator(3.5f));
        anim_cover.setInterpolator(new FastOutSlowInInterpolator());
        anim_cover.setStartOffset(40);
        MovieCoverImg.setAnimation(anim_cover);
        play_fab.setAnimation(anim_fab);
        //MovieThumbnailImg.setAnimation(anim_thumb);

    }


}
