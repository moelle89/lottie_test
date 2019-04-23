package com.moelle.deepdarkness;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;
import com.airbnb.lottie.LottieAnimationView;
import com.github.paolorotolo.appintro.AppIntro;
import com.moelle.deepdarkness.fragment.FirstSlide;
import com.moelle.deepdarkness.util.SampleSlide;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LottieIntro extends AppIntro {

    private SampleSlide slide_1;
    private SampleSlide slide_2;
    private SampleSlide slide_3;
    private SampleSlide slide_4;
    private SampleSlide slide_5;
    Fragment slide_6;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_1 = SampleSlide.newInstance(R.layout.slide_01);
        slide_2 = SampleSlide.newInstance(R.layout.slide_02);
        slide_3 = SampleSlide.newInstance(R.layout.slide_03);
        slide_4 = SampleSlide.newInstance(R.layout.slide_04);
        slide_5 = SampleSlide.newInstance(R.layout.slide_05);

        slide_6 = new FirstSlide();

        addSlide(slide_1);
        addSlide(slide_2);
        addSlide(slide_3);
        addSlide(slide_4);
        addSlide(slide_5);

        setProgressIndicator();
        setImmersiveMode(true);
        setSwipeLock(false);
        setGoBackLock(true);
        setNextArrowColor(getResources().getColor(R.color.background));
        setNavBarColor(R.color.background);
        showStatusBar(true);
        showSkipButton(true);
        setBackButtonVisibilityWithDone(false);
        setCustomTransformer(new ZoomOutPageTransformer());
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        launchHomeScreen();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchHomeScreen();
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.75f;
        private static final float MIN_ALPHA = 0.0f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) {
                view.setAlpha(0);

            } else if (position <= 1) {
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                view.setAlpha(0);
            }
        }
    }

    private void launchHomeScreen() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }

    private void next() {
        pager = findViewById(R.id.view_pager);
        pager.goToNextSlide();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment==null) && (newFragment==slide_1) || (oldFragment==slide_2) && (newFragment==slide_1)) {
            LottieAnimationView animationView1 = findViewById(R.id.animation_view1);
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
                    next();
                }
            });
        }

        else if((oldFragment==slide_1) && (newFragment==slide_2) || (oldFragment==slide_3) && (newFragment==slide_2)){
            LottieAnimationView animationView2 = findViewById(R.id.animation_view2);
            animationView2.setMinFrame(0);
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

        else if((oldFragment==slide_2) && (newFragment==slide_3) || (oldFragment==slide_4) && (newFragment==slide_3)){
            LottieAnimationView animationView3 = findViewById(R.id.animation_view3);
            animationView3.setMinFrame(0);
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
                    animationView3.setMinFrame(0);
                    next();
                }
            });
        }
        else if((oldFragment==slide_3) && (newFragment==slide_4) || (oldFragment==slide_5) && (newFragment==slide_4)){
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
                    next();
                }
            });
        }
        else if((oldFragment==slide_4) && (newFragment==slide_5) || (oldFragment==slide_6) && (newFragment==slide_5)){
            LottieAnimationView animationView5 = findViewById(R.id.animation_view5);
            animationView5.playAnimation();
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
                    LottieAnimationView animationView5 = findViewById(R.id.animation_view5);
                    animationView5.setVisibility(View.INVISIBLE);
                    //finalToast();
                    //launchHomeScreen();
                }
            });
        }

    }
    public void finalToast() {
        Toast toast=Toast.makeText(getApplicationContext(),"done!",Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER,0,120);
        View view=toast.getView();
        TextView view1=(view.findViewById(android.R.id.message));
        view1.setTextColor(Color.WHITE);
        view.setBackgroundResource(R.drawable.toast_frame_accent);
        toast.show();
    }

}