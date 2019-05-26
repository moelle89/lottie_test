package com.moelle.deepdarkness;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moelle.deepdarkness.fragment.fragment_1;
import com.moelle.deepdarkness.fragment.fragment_2;
import com.moelle.deepdarkness.fragment.fragment_3;


//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FloatingActionButton fab;
    SwitchCompat myswitch;
    CardView switchcard;
    PrefManager sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        ActivityCompat.requestPermissions(MainActivity.this,
        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        // dark/light mode switch
        sharedpref = new PrefManager(this);
        if (sharedpref.loadNightModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
        // end of dark/light mode switch

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loading the default fragment
        loadFragment(new fragment_1());

        // dark/light mode switch
        switchcard = findViewById(R.id.switchcard);
        myswitch = findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState()) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                } else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
        // end of dark/light mode switch
        fab = findViewById(R.id.fab);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        LottieAnimationView intro = findViewById(R.id.intro);
        intro.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LottieAnimationView intro = findViewById(R.id.intro);
                intro.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        // ini & setup Animations
        Animation anim_nav = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        myswitch.setAlpha(0f);
        switchcard.setAlpha(0f);
        myswitch.animate().alpha(1f).setDuration(600).setStartDelay(500);
        switchcard.animate().alpha(1f).setDuration(500).setStartDelay(500);
        anim_nav.setStartOffset(600);
        anim_nav.setInterpolator(new OvershootInterpolator(2.8f));
        navigation.setAnimation(anim_nav);
        anim_fab.setStartOffset(680);
        anim_fab.setInterpolator(new OvershootInterpolator(3.5f));
        fab.setAnimation(anim_fab);
    }

        private int previousSelectedId = 0;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            int id = item.getItemId();
            if(previousSelectedId == id) {
                return true;
            }else{
                previousSelectedId = id;
            switch (item.getItemId()) {
                case R.id.nav_1:
                    fragment = new fragment_1();
                    break;

                case R.id.nav_2:
                    fragment = new fragment_2();
                    break;

                case R.id.nav_3:
                    fragment = new fragment_3();
                    break;
            }}

            return loadFragment(fragment);
        }

        private boolean loadFragment(Fragment fragment) {
            //switching fragment
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        }
        private void showDiag() {

            final View dialogView = View.inflate(this,R.layout.dialog,null);

            final Dialog dialog = new Dialog(this,R.style.DialogStyle);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(dialogView);


            ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    revealShow(dialogView, false, dialog);
                }
            });

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    revealShow(dialogView, true, null);
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (i == KeyEvent.KEYCODE_BACK){

                        revealShow(dialogView, false, dialog);
                        return true;
                    }

                    return false;
                }
            });

            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.show();

        }
        private void revealShow(View dialogView, boolean b, final Dialog dialog) {

            final View view = dialogView.findViewById(R.id.dialog);

            int w = view.getWidth();
            int h = view.getHeight();

            int endRadius = (int) Math.hypot(w, h);

            int cx = (int) (fab.getX() + (fab.getWidth()/2));
            int cy = (int) (fab.getY())+ fab.getHeight();


            if(b){
                Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

                view.setVisibility(View.VISIBLE);
                revealAnimator.setDuration(800);
                revealAnimator.start();


            } else {

                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        dialog.dismiss();
                        view.setVisibility(View.INVISIBLE);

                    }
                });
                anim.setDuration(800);
                anim.start();

            }
        }
        public void restartApp () {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            finish();
        }
    }
