package com.moelle.deepdarkness;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

import com.daimajia.easing.quad.QuadEaseIn;
import com.daimajia.easing.quad.QuadEaseInOut;

/**
 * Created by Marwa on 7/8/2018.
 */


public class AnimationPack {

    /**
     * This method grows the element view (ImageView)
     */
    public static void scaleOut(View view) {
        view.animate().scaleX(1.1f).scaleY(1.1f).setDuration(2000);
    }

    /**
     * This method shrinks the element view (ImageView)
     */
    public static void scaleIn(View view) {
        view.setAlpha(0f);
        view.setScaleX(1.5f);
        view.setScaleY(1.5f);
        view.setTranslationY(50);
        view.setRotation(-25);
        view.animate().translationY(0).scaleX(1).scaleY(1).setStartDelay(200).setDuration(800).setInterpolator(new FastOutSlowInInterpolator());
        view.animate().rotation(0).setStartDelay(200).setDuration(800).setInterpolator(new OvershootInterpolator(2));
        view.animate().setStartDelay(200).alpha(1f).setDuration(1400);
    }

    /**
     * This method grows or shrinks the element view (ImageView)
     *
     * @param x        scale of the view in the x direction
     * @param y        scale of the view in the y direction
     * @param duration The length of ensuing property animations, in milliseconds. The value cannot be negative.
     */
    public static void scale(View view, float x, float y, long duration) {
        view.animate().scaleX(x).scaleY(y).setDuration(duration);
    }

    /**
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

    /**
     * This method move the element view to the bottom (ImageView)
     */
    public static void moveToTop(View view, long delay) {
        view.setAlpha(0f);
        view.setTranslationY(60f);
        view.animate().setStartDelay(delay).alpha(1f).translationY(0f).setDuration(600).setInterpolator(new DecelerateInterpolator());
    }

    /**
     * This method move the element view to the top (ImageView)
     */
    public static void moveToBottom(View view, long delay) {
        view.setAlpha(0f);
        view.setTranslationY(-60f);
        view.animate().setStartDelay(delay).alpha(1f).translationY(0f).setDuration(600).setInterpolator(new DecelerateInterpolator());;
    }

    /**
     * This method move the element view to the tob or bottom (ImageView)
     *
     * @param y        the amount to be animated by vertically. The value can be positive or negative.
     * @param duration The length of ensuing property animations, in milliseconds. The value cannot be negative.
     */
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
    public static void rotateRightOrLeft(View view, float value, long duration) {
        view.animate().rotation(value).setDuration(duration);
    }

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

