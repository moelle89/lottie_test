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

public class LottieTutorial extends AppIntro {

    private SampleSlide slide_5;
    private SampleSlide slide_6;
    Fragment slide_7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        slide_5 = SampleSlide.newInstance(R.layout.slide_05);
        slide_6 = SampleSlide.newInstance(R.layout.slide_06);

        slide_7 = new FirstSlide();

        addSlide(slide_5);
        addSlide(slide_6);

        setProgressIndicator();
        setImmersiveMode(true);
        setSwipeLock(false);
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
        launchDashboard();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        launchDashboard();
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

    private void launchDashboard() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);

        if ((oldFragment==null) && (newFragment==slide_5) || (oldFragment==slide_6) && (newFragment==slide_5)) {
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
                }
            });
        }

        if((oldFragment==slide_5) && (newFragment==slide_6) || (oldFragment==slide_7) && (newFragment==slide_6)){
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
                    LottieAnimationView animationView6 = findViewById(R.id.animation_view6);
                    animationView6.setVisibility(View.INVISIBLE);
                    launchDashboard();
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