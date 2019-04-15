package com.moelle.deepdarkness;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import com.airbnb.lottie.LottieAnimationView;
import com.matthewtamlin.sliding_intro_screen_library.buttons.IntroButton;
import com.matthewtamlin.sliding_intro_screen_library.core.IntroActivity;
import com.matthewtamlin.sliding_intro_screen_library.core.LockableViewPager;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;


public class AppIntroActivity extends IntroActivity {

    private static final float[] ANIMATION_TIMES = new float[]{

            0.00f,
            0.33f,
            0.66f,
            1.0f,
            1f

    };
    private LottieAnimationView animationView;
    private LockableViewPager viewPager;

    @Override
    protected Collection<? extends Fragment> generatePages(Bundle savedInstanceState) {
        return new ArrayList<EmptyFragment>() {{
            add(EmptyFragment.newInstance());
            add(EmptyFragment.newInstance());
            add(EmptyFragment.newInstance());
            add(EmptyFragment.newInstance());
            
        }};
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        animationView = (LottieAnimationView) LayoutInflater.from(this).inflate(R.layout.app_intro_animation_view, getRootView(), false);
        viewPager = (LockableViewPager) findViewById(com.matthewtamlin.sliding_intro_screen_library.R.id.intro_activity_viewPager);
        getRootView().addView(animationView, 0);
        setViewPagerScroller();
        addPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setAnimationProgress(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }

    @Override
    protected IntroButton.Behaviour generateFinalButtonBehaviour() {
        return new IntroButton.Behaviour() {
            @Override
            public void setActivity(IntroActivity activity) {
                startActivity(new Intent(AppIntroActivity.this, FirstActivity.class));
                finish();
            }

            @Override
            public IntroActivity getActivity() {
                return null;
            }

            @Override
            public void run() {
            }

        };
    }

    @Override
    public boolean progressIndicatorAnimationsAreEnabled() {
        return true;
    }

    private void setAnimationProgress(int position, float positionOffset) {

        float startProgress = ANIMATION_TIMES[position];
        float endProgress = ANIMATION_TIMES[position + 1];
        float lerb = lerp(startProgress, endProgress, positionOffset);
        animationView.setProgress(lerb);


    }


    private float lerp(float startValue, float endValue, float f) {
        return startValue + f * (endValue - startValue);
    }

    public static final class EmptyFragment extends Fragment {


        private static EmptyFragment newInstance() {
            return new EmptyFragment();

           
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_empty, container, false);


        }
    }

    private void setViewPagerScroller() {
        //noinspection TryWithIdenticalCatches
        try {
            Field scrollerField = ViewPager.class.getDeclaredField("mScroller");
            scrollerField.setAccessible(true);
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true);

            Scroller scroller = new Scroller(this, (Interpolator) interpolator.get(null)) {
                @Override
                public void startScroll(int startX, int startY, int dx, int dy, int duration) {
                    super.startScroll(startX, startY, dx, dy, duration *7);
                }
            };
            scrollerField.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            // Do nothing.
        } catch (IllegalAccessException e) {
            // Do nothing.
        } 
    }}