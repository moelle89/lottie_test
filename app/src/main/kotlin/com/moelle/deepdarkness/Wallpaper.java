package com.moelle.deepdarkness;

import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.moelle.deepdarkness.adapters.WallItemClickListener;
import com.moelle.deepdarkness.adapters.WallpaperAdapter;
import com.moelle.deepdarkness.models.Wall;
import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.calligraphy3.FontMapper;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.moelle.deepdarkness.AnimationPack.scaleIn;

public class Wallpaper extends AppCompatActivity implements WallItemClickListener {

    private RecyclerView WallsRV;
    private Drawable drawable1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

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
        // Get duration scale from the global settings.
        try {
            ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 0.7f);
        } catch (Throwable t) {
            Toast toast = Toast.makeText(getApplicationContext(), "Let's get the hell outta here.", Toast.LENGTH_LONG);
            toast.show();
        }

        setContentView(R.layout.activity_wallpaper);
        View view = findViewById(R.id.wallpaper);
        scaleIn(view);
        WallsRV = findViewById(R.id.Rv_walls);
        // Recyclerview Setup

        // ini data
        List<Wall> listWalls = new ArrayList<>();
        listWalls.add(new Wall("W5", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W6", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W7", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W8", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W9", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W10", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W11", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W12", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W13", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W14", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));
        listWalls.add(new Wall("W15", "https://github.com/LukeSmithxyz/wallpapers/raw/master/Landscapes/1390920427025.jpg"));

        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(this, listWalls, this);
        WallsRV.setAdapter(wallpaperAdapter);
        WallsRV.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    public void onMovieClick(Wall wall, ImageView movieImageView) {
        // here we send wall information to detail activity
        // also we ll create the transition animation between the two activity

        Intent intent = new Intent(this, WallpaperDetail.class);
        // send wall information to deatilActivity
        intent.putExtra("title", wall.getTitle());
        intent.putExtra("imgURL", wall.getThumbnailUrl());
        intent.putExtra("imgCover", wall.getCoverPhotoUrl());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Wallpaper.this,
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());


        // i l make a simple test to see if the click works

        //Toast.makeText(this,"item clicked : " + wall.getTitle(),Toast.LENGTH_LONG).show();
        // it works great

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

}