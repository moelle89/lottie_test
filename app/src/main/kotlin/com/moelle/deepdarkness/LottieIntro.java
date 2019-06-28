package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.github.paolorotolo.appintro.AppIntro;
import com.moelle.deepdarkness.util.SampleSlide;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.calligraphy3.FontMapper;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class LottieIntro extends AppIntro {

    private boolean nextButtonReplaced = false;

    private ConstraintLayout slide1,slide2;

    private SampleSlide slide_1;
    private SampleSlide slide_2;
    private SampleSlide slide_3;
    private SampleSlide slide_4;
    private SampleSlide slide_5;
    private SampleSlide slide_null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        slide_1 = SampleSlide.newInstance(R.layout.slide_01);
        slide_2 = SampleSlide.newInstance(R.layout.slide_02);
        slide_3 = SampleSlide.newInstance(R.layout.slide_03);
        slide_4 = SampleSlide.newInstance(R.layout.slide_04);
        slide_5 = SampleSlide.newInstance(R.layout.slide_05);
        slide_null = SampleSlide.newInstance(R.layout.slide_null);

        addSlide(slide_1);
        addSlide(slide_2);
        addSlide(slide_3);
        addSlide(slide_4);
        addSlide(slide_null);

        setProgressIndicator();
        setImmersiveMode(true);
        setSwipeLock(true);
        setGoBackLock(true);
        setColorDoneText(getResources().getColor(R.color.md_white));
        setColorSkipButton(getResources().getColor(R.color.lighter_gray));
        setNavBarColor(R.color.md_black);
        showStatusBar(true);
        showSkipButton(true);
        setBackButtonVisibilityWithDone(false);

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        Boolean isFirstRun2 = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun2", true);

        if (isFirstRun2) {
            //show start activity
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun2", false).apply();
            launchDashboard();
        }
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        finish();
    }

    private void launchDashboard() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private void launchTutorial() {
        Intent i = new Intent(getApplicationContext(), LottieTutorial.class);
        startActivity(i);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment == null) && (newFragment == slide_1) || (oldFragment == slide_2) && (newFragment == slide_1)) {
            LottieAnimationView animationView1 = findViewById(R.id.animation_view1);
            animationView1.playAnimation();

            slide1 = findViewById(R.id.slide1);
            slide1.animate().alpha(0f).setDuration(400).setStartDelay(7900).start();

            animationView1.addAnimatorListener(new Animator.AnimatorListener() {
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
                    pager.goToNextSlide();
                }
            });
        }

        if ((oldFragment == slide_1) && (newFragment == slide_2) || (oldFragment == slide_3) && (newFragment == slide_2)) {
            LottieAnimationView animation_view2 = findViewById(R.id.animation_view2);
            animation_view2.playAnimation();
            slide2 = findViewById(R.id.slide2);
            slide2.animate().alpha(0f).setDuration(400).setStartDelay(7800).start();

            animation_view2.addAnimatorListener(new Animator.AnimatorListener() {
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
                    LottieAnimationView animation_view2 = findViewById(R.id.animation_view2);
                    animation_view2.setVisibility(View.INVISIBLE);
                }
            });
        }

        if ((oldFragment == slide_2) && (newFragment == slide_3) || (oldFragment == slide_4) && (newFragment == slide_3)) {
            LottieAnimationView animationView3 = findViewById(R.id.animation_view3);
            animationView3.playAnimation();
            animationView3.addAnimatorListener(new Animator.AnimatorListener() {
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
                    LottieAnimationView animationView3 = findViewById(R.id.animation_view3);
                    animationView3.setVisibility(View.INVISIBLE);
                    pager.goToNextSlide();
                }
            });
        }
        if ((oldFragment == slide_3) && (newFragment == slide_4) || (oldFragment == slide_5) && (newFragment == slide_4)) {
            LottieAnimationView animationView4 = findViewById(R.id.animation_view4);
            animationView4.playAnimation();
            animationView4.addAnimatorListener(new Animator.AnimatorListener() {
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
                    Boolean isFirstRun2 = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                            .getBoolean("isFirstRun2", true);

                    if (isFirstRun2) {
                        //show start activity
                        LottieAnimationView animationView4 = findViewById(R.id.animation_view4);
                        animationView4.setVisibility(View.INVISIBLE);
                        pager.goToNextSlide();
                    }
                    else {
                    LottieAnimationView animationView4 = findViewById(R.id.animation_view4);
                    animationView4.setVisibility(View.INVISIBLE);
                    finish();}
                }
            });
        }
        if ((oldFragment == slide_4) && (newFragment == slide_null) || (oldFragment == slide_5) && (newFragment == slide_null)) {
            // Checking for first time launch - before calling setContentView()
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun2", false).apply();
            launchTutorial();
        }
        boolean isLastSlide = pager.getCurrentItem() == (slidesNumber - 1);
        // replace image for next-button with text
        if (!nextButtonReplaced && !isLastSlide) {
            View oldNextButton = findViewById(R.id.next);
            ViewGroup buttonParent = (ViewGroup) oldNextButton.getParent();
            int index = buttonParent.indexOfChild(oldNextButton);
            buttonParent.removeView(oldNextButton);

            TextView newNextButton = (TextView) getLayoutInflater().inflate(R.layout.appintro_button_null, buttonParent, false);
            newNextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            newNextButton.setTextColor(Color.parseColor("#000000"));
            buttonParent.addView(newNextButton, index);

            nextButtonReplaced = true;
        } else {
            View newNextButton = findViewById(R.id.next);
            setButtonState(newNextButton, !isLastSlide);
        }
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
}