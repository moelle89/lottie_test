package com.moelle.deepdarkness;

import android.animation.Animator;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends Activity {

    private PrefManager prefManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        // Checking for first time launch - before calling setContentView()
        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchDashboard();
            finish();
        }
        LottieAnimationView animationView = findViewById(R.id.animation_splash);
        animationView.playAnimation();
        animationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LottieAnimationView animationView = findViewById(R.id.animation_splash);
                animationView.setVisibility(View.INVISIBLE);
                jump();
            }
        });

    }

    private void jump() {
        prefManager.setFirstTimeLaunch(false);
        launchIntroScreen();
    }

    private void launchDashboard() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(this, MainActivity.class));
    }

    private void launchIntroScreen() {
        startActivity(new Intent(this, LottieIntro.class));
    }
}