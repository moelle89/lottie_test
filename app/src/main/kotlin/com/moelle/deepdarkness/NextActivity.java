package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewAnimationUtils;
import com.bumptech.glide.Glide;
import com.klinker.android.simple_videoview.SimpleVideoView;

import android.widget.ImageView;

public class NextActivity extends AppCompatActivity {


    private static final String SAMPLE_VIDEO =
            "https://bitbucket.org/moelle/subs_template/raw/066735ab72c435c07b0c20dd28c06345f05553fc/app/src/main/res/tutorial.mp4";

    private SimpleVideoView videoView;

    int height;
    int width;
    FloatingActionButton mFab;
    ConstraintLayout mConstraintLayout;
    int duration = 500;
    Transition sharedElementEnterTransition;
    Transition.TransitionListener mTransitionListener;

    @Override
    public void onBackPressed() {

        sharedElementEnterTransition.removeListener(mTransitionListener);
        setAnim(mConstraintLayout, false);
        setFab(mFab, true);
    }
    public void getsubstratum(View view) { ////button to launch playstore theme page
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum")); ////Insert your link
        rateLink.setPackage("com.android.vending");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    public void getaddon(View view) { ////button to launch playstore theme page
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.sungstratum")); ////Insert your link
        rateLink.setPackage("com.android.vending");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.sungstratum")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    public void tutorial(View view) { ////button to launch youtube tutorial
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/LmUmwx4x9HI")); ////Insert your link
        rateLink.setPackage("com.google.android.youtube");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/LmUmwx4x9HI")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    //............................................................................................................................

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        videoView = findViewById(R.id.video);
        videoView.setErrorTracker(new SimpleVideoView.VideoPlaybackErrorTracker() {
            @Override
            public void onPlaybackError(Exception e) {
                e.printStackTrace();
                Snackbar.make(videoView, "Uh oh, error playing!", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        videoView.start(SAMPLE_VIDEO);
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoView.isPlaying())
                    videoView.pause();
                else
                    videoView.play();
            }
        });


        getWindow().setEnterTransition(null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        mFab = findViewById(R.id.next_fab);

        mConstraintLayout = findViewById(R.id.bg);

        sharedElementEnterTransition = getWindow().getSharedElementEnterTransition();
        ImageView howtotitle = findViewById(R.id.howtotitlezero);
        Glide.with(this).load("https://i.imgur.com/dB5GrnY.gif").into(howtotitle);

        ImageView youtube = findViewById(R.id.youtube);
        Glide.with(this).load("https://i.imgur.com/W5KZnhH.gif").into(youtube);

        //ImageView head = findViewById(R.id.head);
        //Glide.with(this).load("https://i.imgur.com/oZWR09y.gif").into(head);

        mTransitionListener = new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                setAnim(mConstraintLayout, true);
                setFab(mFab, false);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        };

        sharedElementEnterTransition.addListener(mTransitionListener);


    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setAnim(final View myView, boolean isShow) {
        // previously invisible view

// get the center for the clipping circle
        int cx = mFab.getWidth() / 2;
        int cy = mFab.getHeight() / 2;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(width, height);

        int[] startingLocation = new int[2];
        mFab.getLocationInWindow(startingLocation);

// create the animator for this view (the start radius is zero)
        Animator anim;
        if (isShow) {
            anim =
                    ViewAnimationUtils.createCircularReveal(myView, (int) (mFab.getX() + cx), (int) (mFab.getY() + cy), 0, finalRadius);
            // make the view visible and start the animation
            myView.setVisibility(View.VISIBLE);
        } else {
            anim =
                    ViewAnimationUtils.createCircularReveal(myView, (int) (mFab.getX() + cx), (int) (mFab.getY() + cy), finalRadius, 0);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    myView.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
        }

        anim.setDuration(duration);
        anim.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void setFab(final View myView, boolean isShow) {

// get the center for the clipping circle
        int cx = myView.getWidth() / 2;
        int cy = myView.getHeight() / 2;

// get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(cx, cy);
        Animator anim;
        if (isShow) {
// create the animation (the final radius is zero)
            anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, initialRadius);
// make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.VISIBLE);
                    finishAfterTransition();
                }
            });
            anim.setDuration(duration);
        } else {
            anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);
// make the view invisible when the animation is done
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });
        }
// start the animation
        anim.start();

    }
    @Override
    protected void onStop() {
        super.onStop();
        videoView.release();
    }
    public void close(View view) { ////button to go back to firstactivity
        finish();
    }

}