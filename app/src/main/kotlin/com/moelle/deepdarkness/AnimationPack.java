package com.moelle.deepdarkness;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

/**
 * Created by Marwa on 7/8/2018.
 */


public class AnimationPack {
    /* */
    public static void fadeIn(View view, long delay) {
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(600).setStartDelay(delay).start();}
    /* */
    /* */
    public static void fadeOut(View view, long delay) {
        view.animate().alpha(0f).setDuration(600).setStartDelay(delay).start();}
    /* */
    public static void fadeInCustom(View view, long duration, long delay) {
        view.setAlpha(0f);
        view.animate().alpha(1f).setDuration(duration).setStartDelay(delay).start();}
    /* */
    public static void scale(View view, float x, float y, long duration) {
        view.animate().scaleX(x).scaleY(y).setDuration(duration);}
    /* */
    public static void scaleOut(View view, long delay){
        view.animate().setStartDelay(delay).scaleX(1.3f).scaleY(1.3f).setDuration(550).start();}
    /* */
    public static void scaleIn(View view) {
        view.setAlpha(0f);
        view.setScaleX(1.5f);
        view.setScaleY(1.5f);
        view.setTranslationY(50);
        view.setRotation(-25);
        view.animate().alpha(1f).setDuration(1800);
        view.animate().translationY(0).scaleX(1).scaleY(1).setStartDelay(200).setDuration(700).setInterpolator(new DecelerateInterpolator(2));
        view.animate().rotation(0).setStartDelay(200).setDuration(700).setInterpolator(new OvershootInterpolator(2)).start();
        }
   /* */
   public static void dialogEnter(View view, float scaleX, float scaleY, long duration, long delay, float tension) {
       view.setAlpha(0f);
       view.setScaleX(scaleX);
       view.setScaleY(scaleY);
       view.animate().alpha(1f).scaleX(1).scaleY(1).setStartDelay(delay).setDuration(duration).setInterpolator(new DecelerateInterpolator(tension));
   }
    /* */
    public static void textInOut(View view, float scaleX, float scaleY, long duration, long delay,long delay2, float tension) {
        view.setAlpha(0f);
        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
        view.animate().alpha(1f).scaleX(1).scaleY(1).setStartDelay(delay).setDuration(duration).setInterpolator(new DecelerateInterpolator(tension));
        view.animate().alpha(0f).setDuration(300).setStartDelay(delay2).start();
    }
    /* */

   public static void moveToTop(View view, float y, long delay, float tension) {
       view.setAlpha(0f);
       view.setTranslationY(y);
       view.animate().setStartDelay(delay).alpha(1f).translationY(0f).setDuration(550).setInterpolator(new OvershootInterpolator(tension)).start();}
    /* */
    public static void moveToBottom(View view, float alpha, float y, long delay, float tension) {
        view.setAlpha(0f);
        view.setTranslationY(-y);
        view.animate().setStartDelay(delay).alpha(alpha).translationY(0f).setDuration(550).setInterpolator(new OvershootInterpolator(tension)).start();}
    /* */
    public static void coverAnim(View view, float alpha, float scaleX, float scaleY, float y, long delay) {
        view.setAlpha(0f);
        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
        view.setTranslationY(y);
        view.animate().scaleX(1).scaleY(1).setStartDelay(delay).alpha(alpha).translationY(0f).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();}
    /* */

    /* */
   public static void rotateRightOrLeft(View view, float value, long duration) {
       view.animate().rotation(value).setDuration(duration);
   }
   /*
     * This method move the element view to the right (ImageView)
     */
    public static void moveToRight(View view) {
        view.animate().translationXBy(60f).setDuration(1000);
    }
    /**
     * This method move the element view to the left (ImageView)
     */
    public static void moveToLeft(View view) {
        view.animate().translationXBy(-60f).setDuration(1000);
    }
    /**
     * This method move the element view to the right or left (ImageView)
     *
     * @param x        the amount to be animated by horizontally. The value can be positive or negative.
     * @param duration The length of ensuing property animations, in milliseconds. The value cannot be negative.
     */
    public static void moveToRightOrLeft(View view, float x, long duration) {
        view.animate().translationXBy(x).setDuration(duration);
    }
    /* */
    public static void moveToTobOrBottom(View view, float y, long duration) {
        view.animate().translationYBy(y).setDuration(duration);
    }
    /**
     * This method rotate the element view to the right (ImageView)
     */
    public static void rotateToRight(View view) {
        view.animate().rotation(360f).setDuration(2000);
    }

    /**
     * This method rotate the element view to the left (ImageView)
     */
    public static void rotateToLeft(View view) {
        view.animate().rotation(-360f).setDuration(2000);
    }

    /**
     * This method move the element view to the tob or bottom (ImageView)
     *
     * @param value    rotate right or left. The value can be positive or negative.
     * @param duration The length of ensuing property animations, in milliseconds. The value cannot be negative.
     */

    /**
     * This method rotate the element view upside down (ImageView)
     */
    public static void rotateUpSideDown(View view) {
        view.animate().rotation(180f).setDuration(2000);
    }

    /**
     * This method move the element view upside down right or left (ImageView)
     *
     * @param value    rotate upside down right or left. The value can be positive or negative.
     * @param duration The length of ensuing property animations, in milliseconds. The value cannot be negative.
     */
    public static void rotateUpSideDown(View view, float value, long duration) {
        view.animate().rotation(value).setDuration(duration);
    }


}