package com.moelle.deepdarkness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.moelle.deepdarkness.util.SampleSlide;
import com.github.paolorotolo.appintro.AppIntro;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by andrew on 11/17/16.
 */

public class LottieIntro extends AppIntro {

    private SampleSlide slide_1;
    private SampleSlide slide_2;
    private SampleSlide slide_3;

    private PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_1 = SampleSlide.newInstance(R.layout.slide_01);
        slide_2 = SampleSlide.newInstance(R.layout.slide_02);
        slide_3 = SampleSlide.newInstance(R.layout.slide_03);

        addSlide(slide_1);
        addSlide(slide_2);
        addSlide(slide_3);

        setProgressIndicator();
        showStatusBar(false);
        showSkipButton(true);
        setBackButtonVisibilityWithDone(true);
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
        private static final float MIN_SCALE = 0.8f;
        private static final float MIN_ALPHA = 0.0f;

        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }

    private void launchHomeScreen() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment==null) && (newFragment==slide_1) || (oldFragment==slide_2) && (newFragment==slide_1)) {

            LottieAnimationView animationView1 = findViewById(R.id.animation_view1);
            animationView1.playAnimation();

        }

        else if((oldFragment==slide_1) && (newFragment==slide_2) || (oldFragment==slide_3) && (newFragment==slide_2)){

            LottieAnimationView animationView2 = findViewById(R.id.animation_view2);
            animationView2.playAnimation();

        }

        else if((oldFragment==slide_2) && (newFragment==slide_3)){

            LottieAnimationView animationView3 = findViewById(R.id.animation_view3);
            animationView3.playAnimation();
        }

    }
}