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

import static com.moelle.deepdarkness.AnimationPack.dialogEnter;

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
            ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 0.75f);
        } catch (Throwable t) {
            Toast toast = Toast.makeText(getApplicationContext(), "Let's get the hell outta here.", Toast.LENGTH_LONG);
            toast.show();
        }

        setContentView(R.layout.activity_wallpaper);
        View view = findViewById(R.id.wallpaper);
        dialogEnter(view,1.2f,1.2f,600,0,2);
        WallsRV = findViewById(R.id.Rv_walls);
        // Recyclerview Setup

        // ini data
        List<Wall> listWalls = new ArrayList<>();
        listWalls.add(new Wall("W5", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/5a8b1c78652deaeaab261c60/1534271521472/273WEb.jpg"));
        listWalls.add(new Wall("W6", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/5a34055f53450a1651a4662d/1534271523191/265WEB.jpg"));
        listWalls.add(new Wall("W7", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/5a223b10e2c483bcf3a1f02f/1534271521656/Web_262.jpg"));
        listWalls.add(new Wall("W8", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/5a223b2ee4966bbfda19cae6/1534271521513/WEB-263.jpg"));
        listWalls.add(new Wall("W9", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/59dd8e34e9bfdf3c309b887f/1534271522123/260_2.jpg"));
        listWalls.add(new Wall("W10", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/598184aaf9a61e29dd5a1581/1534271523516/248.jpg"));
        listWalls.add(new Wall("W11", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/596470ecff7c5099c12deb48/1534271524263/243.jpg"));
        listWalls.add(new Wall("W12", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/596470df3a04119a4095ee87/1534271524286/WEB_242.jpg"));
        listWalls.add(new Wall("W13", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/5964727bbe659459b41a5310/1534271523737/240.jpg"));
        listWalls.add(new Wall("W14", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/59489a9515d5db5628ceeb93/1534271522013/236.jpg"));
        listWalls.add(new Wall("W15", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/593b66a33a04116ac557d17a/1534271522362/226_2.jpg"));
        listWalls.add(new Wall("W16", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/591270721e5b6cb37472ef9b/1534271522993/208_3.jpg"));
        listWalls.add(new Wall("W17", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/590d5815f5e23141032aa6fe/1534271523143/205.jpg"));
        listWalls.add(new Wall("W18", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/590d57f829687fab6af689b5/1534271524128/203.jpg"));
        listWalls.add(new Wall("W19", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/590d5687440243109d128bb8/1534271523063/200.jpg"));
        listWalls.add(new Wall("W20", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/58ff848bb3db2bff3b236af1/58ff84a959cc68ed099948ed/1534271523159/198.jpg"));
        listWalls.add(new Wall("W21", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/5813773debbd1a7bb4a43558/587eebc3725e255a02e9b9b3/1493051330473/106.jpg"));
        listWalls.add(new Wall("W22", "https://static1.squarespace.com/static/57fa538f414fb528b4122d54/5813773debbd1a7bb4a43558/588988f359cc685fe451cadc/1493051331092/113.jpg"));

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