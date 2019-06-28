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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.calligraphy3.FontMapper;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

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
        //Custom Fonts Ini
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Khula-ExtraBold.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .setFontMapper(new FontMapper() {
                                    @Override
                                    public String map(String font) {
                                        return font;
                                    }
                                })
                                .build()))
                .build());
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
                Toast toast = new Toast(getBaseContext());
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.custom_toast_text,null);
                TextView textView = view.findViewById(R.id.text);
                textView.setText(R.string.wallpaper_set);
                toast.setView(view);
                toast.setGravity(Gravity.BOTTOM, 0, 125 | Gravity.BOTTOM);
                toast.setDuration(Toast.LENGTH_LONG);
                toast.show();
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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}
