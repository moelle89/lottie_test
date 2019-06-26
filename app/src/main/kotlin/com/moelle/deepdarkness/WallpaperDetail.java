package com.moelle.deepdarkness;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

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
        final String imgURL = getIntent().getExtras().getString("imgURL");
        String imgCover = getIntent().getExtras().getString("imgCover");
        MovieThumbnailImg = findViewById(R.id.detail_wall_img);

        Glide.with(this)
                .load(imgURL)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(14)))
                .into(MovieThumbnailImg);
        MovieCoverImg = findViewById(R.id.detail_wall_cover);
        MovieCoverImg.setAlpha(0f);
        Glide.with(this).load(imgCover).into(MovieCoverImg);
        // setup animation
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        AnimationPack.coverAnim(MovieCoverImg,1f,2.6f,2.6f,30,180);
        anim_fab.setStartOffset(180);
        anim_fab.setInterpolator(new OvershootInterpolator(3.0f));
        play_fab.setAnimation(anim_fab);
        //MovieThumbnailImg.setAnimation(anim_thumb);

        play_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                new LoadBitmapTask(getApplicationContext(), imgURL).execute();
            }
        });
    }

    static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        if (w <= 0 || h <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    static class LoadBitmapTask extends AsyncTask<Void, Void, Drawable> {
        private WeakReference<Context> contextWeakReference;
        private String imgUrl;

        LoadBitmapTask(Context context, String imgUrl) {
            contextWeakReference = new WeakReference<>(context);
            this.imgUrl = imgUrl;
        }

        @Override
        protected Drawable doInBackground(Void... params) {
            Context context = contextWeakReference.get();
            if (context != null) {
                try {
                    return Glide.with(context)
                            .load(imgUrl)
                            .submit()
                            .get();
                } catch (ExecutionException | InterruptedException ignored) {
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Drawable wallpaper) {
            Context context = contextWeakReference.get();
            if (wallpaper != null && context != null) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
                try {
                    Bitmap bitmap = drawableToBitmap(wallpaper);
                    if (bitmap != null) {
                        wallpaperManager.setBitmap(bitmap);
                    }
                } catch (IOException ignored) {
                }
            }
        }
    }
}
