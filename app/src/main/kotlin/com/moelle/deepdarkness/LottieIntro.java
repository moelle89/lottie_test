package com.moelle.deepdarkness;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.github.paolorotolo.appintro.AppIntro;
import com.moelle.deepdarkness.util.SampleSlide;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class LottieIntro extends AppIntro {

    private boolean nextButtonReplaced = false;
    private PrefManager prefManager;

    private SampleSlide slide_1;
    private SampleSlide slide_2;
    private SampleSlide slide_3;
    private SampleSlide slide_4;
    private SampleSlide slide_5;
    private SampleSlide slide_null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_1 = SampleSlide.newInstance(R.layout.slide_01);
        slide_2 = SampleSlide.newInstance(R.layout.slide_02);
        slide_3 = SampleSlide.newInstance(R.layout.slide_03);
        slide_4 = SampleSlide.newInstance(R.layout.slide_04);
        slide_5 = SampleSlide.newInstance(R.layout.slide_05);
        slide_null = SampleSlide.newInstance(R.layout.dialog);

        addSlide(slide_1);
        addSlide(slide_2);
        addSlide(slide_3);
        addSlide(slide_4);
        addSlide(slide_null);

        setProgressIndicator();
        setImmersiveMode(true);
        setSwipeLock(true);
        setGoBackLock(true);
        setColorDoneText(getResources().getColor(R.color.textColor));
        setColorSkipButton(getResources().getColor(R.color.textColor));
        setNavBarColor(R.color.background);
        showStatusBar(true);
        showSkipButton(true);
        setBackButtonVisibilityWithDone(false);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        launchDashboard();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchTutorial();
    }

    private void launchDashboard() {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

    private void launchTutorial() {
        Intent i = new Intent(getApplicationContext(),LottieTutorial.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment==null) && (newFragment==slide_1) || (oldFragment==slide_2) && (newFragment==slide_1)) {
            LottieAnimationView animationView1 = findViewById(R.id.animation_view1);
            final int fg = ContextCompat.getColor(this,(R.color.textColor));
            SimpleColorFilter filterfg = new SimpleColorFilter(fg);
            KeyPath keyfg = new KeyPath("fg","**");
            LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filterfg);
            animationView1.addValueCallback(keyfg, LottieProperty.COLOR_FILTER, callback);
            animationView1.playAnimation();
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

        if((oldFragment==slide_1) && (newFragment==slide_2) || (oldFragment==slide_3) && (newFragment==slide_2)){
            LottieAnimationView animationView2 = findViewById(R.id.animation_view2);
            animationView2.playAnimation();

            animationView2.addAnimatorListener(new Animator.AnimatorListener() {
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
                    LottieAnimationView animationView2 = findViewById(R.id.animation_view2);
                    animationView2.setVisibility(View.INVISIBLE);
                }
            });
        }

        if((oldFragment==slide_2) && (newFragment==slide_3) || (oldFragment==slide_4) && (newFragment==slide_3)){
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
        if((oldFragment==slide_3) && (newFragment==slide_4) || (oldFragment==slide_5) && (newFragment==slide_4)){
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
                        LottieAnimationView animationView4 = findViewById(R.id.animation_view4);
                        animationView4.setVisibility(View.INVISIBLE);
                        pager.goToNextSlide();
                    }
            });
        }
        if((oldFragment==slide_4) && (newFragment==slide_null) || (oldFragment==slide_5) && (newFragment==slide_null)){
            // Checking for first time launch - before calling setContentView()
            prefManager = new PrefManager(this);
            if (!prefManager.isFirstTimeLaunch()) {
                launchDashboard();
                finish();
            }
            LottieAnimationView animationViewNull = findViewById(R.id.animation_viewNull);
            animationViewNull.playAnimation();
            animationViewNull.addAnimatorListener(new Animator.AnimatorListener() {
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
                    prefManager.setFirstTimeLaunch(false);
                    launchTutorial();
                    finish();
                }
            });
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

}