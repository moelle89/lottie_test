package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.moelle.deepdarkness.fragment.fragment_1;
import com.moelle.deepdarkness.fragment.fragment_2;
import com.moelle.deepdarkness.fragment.fragment_3;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ColorPickerDialogListener {

    private static final String TAG = "MainActivity";
    // Give your color picker dialog unique IDs if you have multiple dialogs.
    private static final int DIALOG_ID = 0;

    public static final int[] DD_Colors = {
            0xFFff385f,  //  Acorn
            0xFF00b7cd,  //  Cyan
            0xFFc7a861,  //  Gold
            0xFF19cc7f,  //  Venom
            0xFFffc12f,  //  Holi
            0xFFf62b48,  //  Infrared
            0xFF90d3b7,  //  Mint
            0xFFafca54,  //  Mojito
            0xFF0dd453,  //  Monster
            0xFF007eff,  //  Octopus
            0xFFFF701C,  //  Orange
            0xFF5eb9ff,  //  Pastel Blue
            0xFFE27C7C,  //  Pastel Red
            0xFFff2c63,  //  Pink
            0xFF9085ff,  //  Purple
            0xFFff4b79,  //  Raspberry
            0xFFFF232D,  //  Red
            0xFF7049f5,  //  Violet
            0xFFced8de,  //  White
            0xFFffc107,  //  Yellow
    };

    private FloatingActionButton fab;
    float durationScale;
    SwitchCompat myswitch;
    CardView switchcard;
    PrefManager sharedpref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Get duration scale from the global settings.
        durationScale = Settings.Global.getFloat(getApplicationContext().getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE, 1);
        // If global duration scale is not 1 (default), try to override it
        if (durationScale != 1) {
            try {ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 0.7f); durationScale = 0.7f;}
            catch (Throwable t) {Toast toast = Toast.makeText(getApplicationContext(), "Let's get the hell outta here.", Toast.LENGTH_LONG); toast.show();}
        }

        // dark/light mode switch
        sharedpref = new PrefManager(this);
        if (sharedpref.loadNightModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
        // end of dark/light mode switch

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loading the default fragment
        loadFragment(new fragment_1());

        // dark/light mode switch
        switchcard = findViewById(R.id.switchcard);
        myswitch = findViewById(R.id.myswitch);
        if (sharedpref.loadNightModeState()) {
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sharedpref.setNightModeState(true);
                    restartApp();
                } else {
                    sharedpref.setNightModeState(false);
                    restartApp();
                }
            }
        });
        // end of dark/light mode switch

        fab = findViewById(R.id.fab);

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        LottieAnimationView intro = findViewById(R.id.intro);
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
        Animation anim_nav = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        Animation anim_fab = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        myswitch.setAlpha(0f);
        switchcard.setAlpha(0f);
        myswitch.animate().alpha(1f).setDuration(600).setStartDelay(500);
        switchcard.animate().alpha(1f).setDuration(500).setStartDelay(500);
        anim_nav.setStartOffset(600);
        anim_nav.setInterpolator(new OvershootInterpolator(2.8f));
        navigation.setAnimation(anim_nav);
        anim_fab.setStartOffset(680);
        anim_fab.setInterpolator(new OvershootInterpolator(3.5f));
        fab.setAnimation(anim_fab);
    }
        private int previousSelectedId = 0;


        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            int id = item.getItemId();
            if(previousSelectedId == id) {
                return true;
            }else{
                previousSelectedId = id;
            switch (item.getItemId()) {
                case R.id.nav_1:
                    fragment = new fragment_1();
                    break;

                case R.id.nav_2:
                    fragment = new fragment_2();
                    break;

                case R.id.nav_3:
                    fragment = new fragment_3();
                    break;
            }}

            return loadFragment(fragment);
        }
        // Settings that should be changed when toggling animations
        private boolean loadFragment(Fragment fragment) {
            //switching fragment
            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
                return true;
            }
            return false;
        }

        private void showDiag() {

            final View dialogView = View.inflate(this,R.layout.dialog_contact,null);

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
            int cy = (int) (fab.getY())+ fab.getHeight();


            if(b){
                Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

                view.setVisibility(View.VISIBLE);
                revealAnimator.setDuration(750);
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
        public void launchPicker(View view) {
            final int Default = ContextCompat.getColor(this, R.color.accent1);
            ColorPickerDialog.newBuilder()
                    .setDialogTitle(R.string.Null)
                    .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                    .setColor(Default)
                    .setPresets(DD_Colors)
                    .setAllowPresets(true)
                    .setDialogId(DIALOG_ID)
                    .setShowColorShades(true)
                    .setAllowCustom(true)
                    .setShowAlphaSlider(false)
                    .show(this);
        }

        public void onColorSelected(int dialogId, @ColorInt int color) {
            Log.d(TAG, "onColorSelected() called with: dialogId = [" + dialogId + "], color = [" + color + "]");
            switch (dialogId) {
                case DIALOG_ID:
                    // We got result from the dialog that is shown when clicking on the icon in the action bar.
                    Toast.makeText(MainActivity.this, "Selected Color: #" + Integer.toHexString(color), Toast.LENGTH_SHORT).show();
                    break;
            }
        }
        public void onDialogDismissed(int dialogId) { }

        public void restartApp () {
            Intent b = new Intent(getApplicationContext(),MainActivity.class);
            finish();
            startActivity(b);
        }
}
