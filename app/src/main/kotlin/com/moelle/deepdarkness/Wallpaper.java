package com.moelle.deepdarkness;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

public class Wallpaper extends AppCompatActivity implements WallItemClickListener {

    private RecyclerView WallsRV ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        setContentView(R.layout.activity_wallpaper);
        WallsRV = findViewById(R.id.Rv_walls);


        // Recyclerview Setup
        // ini data

        List<Wall> lstWalls = new ArrayList<>();
        lstWalls.add(new Wall("W1",R.drawable.w1,R.drawable.w1));
        lstWalls.add(new Wall("W2",R.drawable.w2,R.drawable.w2));
        lstWalls.add(new Wall("W3",R.drawable.w3,R.drawable.w3));
        lstWalls.add(new Wall("W4",R.drawable.w4,R.drawable.w4));
        lstWalls.add(new Wall("W5",R.drawable.w5,R.drawable.w5));
        lstWalls.add(new Wall("W6",R.drawable.w6,R.drawable.w6));
        lstWalls.add(new Wall("W7",R.drawable.w7,R.drawable.w7));
        lstWalls.add(new Wall("W8",R.drawable.w8,R.drawable.w8));
        lstWalls.add(new Wall("W9",R.drawable.w9,R.drawable.w9));
        lstWalls.add(new Wall("W10",R.drawable.w10,R.drawable.w10));
        lstWalls.add(new Wall("W11",R.drawable.w11,R.drawable.w11));
        lstWalls.add(new Wall("W12",R.drawable.w12,R.drawable.w12));
        lstWalls.add(new Wall("W13",R.drawable.w13,R.drawable.w13));
        lstWalls.add(new Wall("W14",R.drawable.w14,R.drawable.w14));
        lstWalls.add(new Wall("W15",R.drawable.w15,R.drawable.w15));
        lstWalls.add(new Wall("W16",R.drawable.w16,R.drawable.w16));
        lstWalls.add(new Wall("W17",R.drawable.w17,R.drawable.w17));
        lstWalls.add(new Wall("W18",R.drawable.w18,R.drawable.w18));
        lstWalls.add(new Wall("W19",R.drawable.w19,R.drawable.w19));
        lstWalls.add(new Wall("W20",R.drawable.w20,R.drawable.w20));
        
        WallpaperAdapter wallpaperAdapter = new WallpaperAdapter(this, lstWalls,this);
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
        intent.putExtra("imgURL", wall.getThumbnail());
        intent.putExtra("imgCover", wall.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Wallpaper.this,
                                                    movieImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        //Toast.makeText(this,"item clicked : " + wall.getTitle(),Toast.LENGTH_LONG).show();
        // it works great


    }


}
