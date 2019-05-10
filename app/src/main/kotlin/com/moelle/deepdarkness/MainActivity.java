package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.moelle.deepdarkness.fragment.fragment_1;
import com.moelle.deepdarkness.fragment.fragment_2;
import com.moelle.deepdarkness.fragment.fragment_3;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    SwitchCompat myswitch;
    CardView switchcard;
    PrefManager sharedpref;

    //Fragments
    fragment_1 fragment1;
    fragment_2 fragment2;
    fragment_3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // dark/light mode switch
        sharedpref = new PrefManager(this);
        if(sharedpref.loadNightModeState()) {
            setTheme(R.style.AppTheme_Dark_NoActionBar);
        }
        else  setTheme(R.style.AppTheme_NoActionBar);
        // end of dark/light mode switch

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dark/light mode switch
        switchcard=findViewById(R.id.switchcard);
        myswitch=findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState()) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                }
                else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
        // end of dark/light mode switch

        fab = findViewById(R.id.fab);
        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);
        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        //final int introBG = ContextCompat.getColor(MainActivity.this, R.color.background);
        LottieAnimationView intro = findViewById(R.id.intro);
        /*SimpleColorFilter filter = new SimpleColorFilter(introBG);
        KeyPath keyPath = new KeyPath("Shape Layer 1", "Fill 1");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        intro.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
        intro.playAnimation();*/

        intro.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                LottieAnimationView intro = findViewById(R.id.intro);
                intro.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        // ini & setup Animations
        //curtain.animate().alpha(0f).setDuration(1500);
        Animation anim_nav = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        myswitch.setAlpha(0f);
        switchcard.setAlpha(0f);
        myswitch.animate().alpha(1f).setDuration(600).setStartDelay(500);
        switchcard.animate().alpha(1f).setDuration(500).setStartDelay(500);
        anim_nav.setStartOffset(600);
        anim_nav.setInterpolator(new OvershootInterpolator(2.8f));
        bottomNavigationView.setAnimation(anim_nav);
        anim_fab.setStartOffset(680);
        anim_fab.setInterpolator(new OvershootInterpolator(3.5f));
        fab.setAnimation(anim_fab);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_call:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_chat:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_contact:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bottomNavigationView.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDiag();

            }
        });
        setupViewPager(viewPager);
    }
    //
    private void showDiag() {

        final View dialogView = View.inflate(this,R.layout.dialog,null);

        final Dialog dialog = new Dialog(this,R.style.DialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);


        ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                revealShow(dialogView, false, dialog);
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(dialogView, true, null);
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK){

                    revealShow(dialogView, false, dialog);
                    return true;
                }

                return false;
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.show();

    }
    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (fab.getX() + (fab.getWidth()/2));
        int cy = (int) (fab.getY())+ fab.getHeight() + 56;


        if(b){
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(800);
            revealAnimator.start();


        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });
            anim.setDuration(800);
            anim.start();

        }

    }
    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        fragment1 = new fragment_1();
        fragment2 = new fragment_2();
        fragment3 = new fragment_3();

        adapter.addFragment(fragment1);
        adapter.addFragment(fragment2);
        adapter.addFragment(fragment3);

        viewPager.setAdapter(adapter);
    }
    public void restartApp () {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
        finish();
    }

}
