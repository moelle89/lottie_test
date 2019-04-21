package com.moelle.deepdarkness;

import android.animation.Animator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.airbnb.lottie.LottieAnimationView;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.model.SliderPage;
import com.moelle.deepdarkness.fragment.FirstSlide;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.moelle.deepdarkness.util.SampleSlide;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LottieIntro extends AppIntro {

    private SampleSlide slide_1;
    private SampleSlide slide_2;
    private SampleSlide slide_3;
    Fragment slide_4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_1 = SampleSlide.newInstance(R.layout.slide_01);
        slide_2 = SampleSlide.newInstance(R.layout.slide_02);
        slide_3 = SampleSlide.newInstance(R.layout.slide_03);
        slide_4 = new FirstSlide();

        addSlide(slide_1);
        addSlide(slide_2);
        addSlide(slide_3);
        addSlide(slide_4);

        SliderPage slide_5 = new SliderPage();
        slide_5.setTitle("Explore");
        slide_5.setDescription("Feel free to explore the rest of the library demo!");
        slide_5.setImageDrawable(R.drawable.background_activity);
        slide_5.setBgColor(Color.TRANSPARENT);
        addSlide(AppIntroFragment.newInstance(slide_5));

        setProgressIndicator();
        setGoBackLock(true);
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

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment==null) && (newFragment==slide_1) || (oldFragment==slide_2) && (newFragment==slide_1)) {
            LottieAnimationView animationView1 = findViewById(R.id.animation_view1);
            animationView1.playAnimation();
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
                    animationView2.setMinFrame(55);
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
                    animationView3.setMinFrame(70);
                }
            });
        }
    }


}