package com.moelle.deepdarkness;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.widget.HorizontalScrollView;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class HorizontalOverScrollView extends HorizontalScrollView {
    private static final int WIDTH_DEVIDER_OVERSCROLL_DISTANCE = 8;

    private TimeInterpolator mInterpolator;
    private int mMaxOverscrollDistance;
    private int mAnimTime;
    private long mStartTime;

    /**
     * Instantiates {@link HorizontalOverScrollView} object.
     */
    public HorizontalOverScrollView(final Context context) {
        super(context);
        init();
    }

    /**
     * Instantiates {@link HorizontalOverScrollView} object.
     */
    public HorizontalOverScrollView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Instantiates {@link HorizontalOverScrollView} object.
     */
    public HorizontalOverScrollView(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;
        mMaxOverscrollDistance = widthPixels / WIDTH_DEVIDER_OVERSCROLL_DISTANCE;
        mAnimTime = 400;
        mInterpolator = new FastOutSlowInInterpolator();
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        int overScrollDistance = mMaxOverscrollDistance;
        if (isTouchEvent) {
            mStartTime = AnimationUtils.currentAnimationTimeMillis();
        } else {
            final long elapsedTime = AnimationUtils.currentAnimationTimeMillis() - mStartTime;
            float interpolation = mInterpolator.getInterpolation((float) elapsedTime / mAnimTime);
            interpolation = interpolation > 1 ? 1 : interpolation;
            overScrollDistance -= overScrollDistance * interpolation;
            overScrollDistance = overScrollDistance < 0 ? 0 : overScrollDistance;
        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, overScrollDistance, maxOverScrollY, isTouchEvent);
    }
}