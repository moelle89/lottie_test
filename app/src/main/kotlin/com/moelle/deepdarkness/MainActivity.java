package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.moelle.deepdarkness.fragment.fragment_1;
import com.moelle.deepdarkness.fragment.fragment_2;
import com.moelle.deepdarkness.fragment.fragment_3;
import com.moelle.deepdarkness.util.PermissionHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ColorPickerDialogListener {

    private static final String TAG = "MainActivity";
    // Give your color picker dialog unique IDs if you have multiple dialogs.
    private static final int DIALOG_ID = 0;
    static int pickedColor = 0;


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

    float durationScale;
    SwitchCompat myswitch;
    CardView switchcard;
    PrefManager sharedpref;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        // Get duration scale from the global settings.
        durationScale = Settings.Global.getFloat(getApplicationContext().getContentResolver(), Settings.Global.ANIMATOR_DURATION_SCALE, 1);
        // If global duration scale is not 1 (default), try to override it
        if (durationScale != 1) {
            try {
                ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 0.7f);
                durationScale = 0.7f;
            } catch (Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Let's get the hell outta here.", Toast.LENGTH_LONG);
                toast.show();
            }
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

                PermissionHelper.checkPermissions(MainActivity.this);
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
        if (previousSelectedId == id) {
            return true;
        } else {
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
            }
        }

        return loadFragment(fragment);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (!PermissionHelper.checkPermissionResult(permissions, grantResults)) {
            Toast.makeText(this, R.string.no_permission, Toast.LENGTH_LONG).show();
        }
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

        final View dialogView = View.inflate(this, R.layout.dialog_contact, null);

        final Dialog dialog = new Dialog(this, R.style.DialogStyle);
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
                if (i == KeyEvent.KEYCODE_BACK) {

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

        int cx = (int) (fab.getX() + (fab.getWidth() / 2));
        int cy = (int) (fab.getY()) + fab.getHeight();


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);

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

    @Override
    public void onColorSelected(int dialogId, @ColorInt int color) {
        Log.d(TAG, "onColorSelected() called with: dialogId = [" + dialogId + "], color = [" + color + "]");
        switch (dialogId) {
            case DIALOG_ID:
                try {
                    createColorBitmapAndSave(1366, 768, color);
                    pickedColor = color;
                    sharedpref = new PrefManager(this);
                    sharedpref.editor.putInt("pickedColor", pickedColor);
                    sharedpref.editor.commit();
                    TEST();
                    Toast toast = new Toast(this);
                    View view = LayoutInflater.from(this).inflate(R.layout.custom_toast, null);
                    CardView card = view.findViewById(R.id.card_toast);
                    card.setCardBackgroundColor(pickedColor);
                    TextView textView = view.findViewById(R.id.text);
                    textView.setText(R.string.success);
                    toast.setView(view);
                    toast.setGravity(Gravity.BOTTOM, 0, 90| Gravity.CENTER);
                    toast.setDuration(Toast.LENGTH_LONG);
                    overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
                    toast.show();


                } catch (IOException e) {
                    Log.e("ANAS", "failed to save file ", e);
                }
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }


    public void restartApp() {
        Intent b = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(b);
    }
    private void TEST() {
        View preview = LayoutInflater.from(this).inflate(R.layout.fragment_2, null);
        CardView previewCard = preview.findViewById(R.id.accent11);
        previewCard.setCardBackgroundColor(pickedColor);
        previewCard.refreshDrawableState();
    }

    private void createColorBitmapAndSave(int width, int height, @ColorInt int color)
            throws IOException {
        //Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //bitmap.eraseColor(color);

        int colors[] = new int[2];
        colors[0] = Color.parseColor("#000000");
        colors[1] = Color.parseColor("#123456");

        LinearGradient gradient = new LinearGradient(0, 0, 0, 768, color, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        Paint p = new Paint();
        p.setDither(true);
        p.setShader(gradient);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(new RectF(0, 0, width, height), p);

        File parent = new File(Environment.getExternalStorageDirectory() + "/"
                + DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/"));
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IOException("failed to create path " + parent);
        }

        File file = new File(parent, Integer.toHexString(color) + ".png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Use Bitmap.CompressFormat.JPEG if you want JPEG
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
    }

}
