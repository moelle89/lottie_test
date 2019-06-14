package com.moelle.deepdarkness;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.github.paolorotolo.appintro.AppIntro;
import com.moelle.deepdarkness.util.SampleSlide;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LottieTutorial extends AppIntro {

    private SampleSlide slide_5;
    private SampleSlide slide_6;
    Fragment slide_7;

    @Override
    public void onDonePressed(Fragment currentFragment) {
        Boolean isFirstRun3 = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun3", true);

        if (isFirstRun3) {
            //show start activity
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun3", false).apply();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // For overlap of Re Entering Activity - MainActivity.java and Exiting TransitionActivity.java
        super.onCreate(savedInstanceState);

        slide_5 = SampleSlide.newInstance(R.layout.slide_05);
        slide_6 = SampleSlide.newInstance(R.layout.slide_06);

        slide_7 = new SampleSlide();

        addSlide(slide_5);
        addSlide(slide_6);

        setImmersiveMode(false);
        setSwipeLock(false);
        setNavBarColor(R.color.colorAccentPromptBackground);
        showStatusBar(false);
        showSkipButton(false);
        setBackButtonVisibilityWithDone(true);
        setFadeAnimation();
        //setCustomTransformer(new ZoomOutPageTransformer());
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment == null) && (newFragment == slide_5) || (oldFragment == slide_6) && (newFragment == slide_5)) {
            LottieAnimationView animationView5 = findViewById(R.id.animation_view5);
            com.moelle.deepdarkness.SimpleColorFilter TEXT1 = new com.moelle.deepdarkness.SimpleColorFilter(getResources().getColor(R.color.textColor), PorterDuff.Mode.CLEAR);
            KeyPath TEXT1KEY = new KeyPath("TEXT1","**");
            LottieValueCallback<ColorFilter> TEXT1CALL = new LottieValueCallback<ColorFilter>(TEXT1);
            animationView5.addValueCallback(TEXT1KEY, LottieProperty.COLOR_FILTER, TEXT1CALL);

            com.moelle.deepdarkness.SimpleColorFilter TEXT2 = new com.moelle.deepdarkness.SimpleColorFilter(getResources().getColor(R.color.secondary_text_light), PorterDuff.Mode.SRC_IN);
            KeyPath TEXT2KEY = new KeyPath("TEXT2","**");
            LottieValueCallback<ColorFilter> TEXT2CALL = new LottieValueCallback<ColorFilter>(TEXT2);
            animationView5.addValueCallback(TEXT2KEY, LottieProperty.COLOR_FILTER, TEXT2CALL);

            animationView5.addValueCallback(
                    new KeyPath("ACCENT","**"),
                    LottieProperty.COLOR_FILTER,
                    new SimpleLottieValueCallback<ColorFilter>() {
                        @Override
                        public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                            return new PorterDuffColorFilter(getResources().getColor(R.color.colorAccent), PorterDuff.Mode.SRC_OVER);
                        }
                    }


                    );

            //animationView5.playAnimation();
            animationView5.addAnimatorListener(new Animator.AnimatorListener() {
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
                }
            });
        }

        if ((oldFragment == slide_5) && (newFragment == slide_6) || (oldFragment == slide_7) && (newFragment == slide_6)) {
            LottieAnimationView animationView6 = findViewById(R.id.animation_view6);
            animationView6.playAnimation();
            animationView6.addAnimatorListener(new Animator.AnimatorListener() {
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
                    //LottieAnimationView animationView6 = findViewById(R.id.animation_view6);
                    //animationView6.setMinFrame(175);
                    //launchDashboard();
                }
            });
        }

    }

}