package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
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
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
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

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.calligraphy3.FontMapper;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.moelle.deepdarkness.AnimationPack.moveToTop;

//implement the interface OnNavigationItemSelectedListener in your activity class
public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, ColorPickerDialogListener {

    private static final String TAG = "MainActivity";
    // Give your color picker dialog unique IDs if you have multiple dialogs.
    private static final int DIALOG_ID1 = 0;
    private static final int DIALOG_ID2 = 1;
    private BottomNavigationView navigation;
    private FrameLayout fragment_container;
    private ConstraintLayout curtain;

    private int pickedColor1;
    private int pickedColor2;
    private int pickedColor3;
    private int pickedColor4;
    SharedPreferences preferences;
    private final String PICKED_COLOR_KEY1 = "picker-key1";
    private final String PICKED_COLOR_KEY2 = "picker-key2";
    private final String PICKED_COLOR_KEY3 = "picker-key3";
    private final String PICKED_COLOR_KEY4 = "picker-key4";

    public static final int[] DD_Colors = {
            0xFF1835FC,  //  Ultramarine
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
            //0xFFff4b79,  //  Raspberry
            0xFFFF232D,  //  Red
            0xFF7049f5,  //  Violet
            0xFFced8de,  //  White
            0xFFffc107,  //  Yellow
    };

    SwitchCompat myswitch;
    CardView switchcard;
    PrefManager sharedpref;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        //Custom Fonts Ini
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Khula-ExtraBold.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .setFontMapper(new FontMapper() {
                                    @Override
                                    public String map(String font) {
                                        return font;
                                    }
                                })
                                .build()))
                .build());

        // Get duration scale from the global settings.
            try {
                ValueAnimator.class.getMethod("setDurationScale", float.class).invoke(null, 0.75f);
            } catch (Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Let's get the hell outta here.", Toast.LENGTH_LONG);
                toast.show();
            }

        // dark/light mode switch
        sharedpref = new PrefManager(this);
        if (sharedpref.loadNightModeState()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
        // end of dark/light mode switch

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        pickedColor1 = preferences.getInt(PICKED_COLOR_KEY1, ContextCompat.getColor(getApplicationContext(), R.color.colorAccent));
        pickedColor2 = preferences.getInt(PICKED_COLOR_KEY2, ContextCompat.getColor(getApplicationContext(), R.color.background));
        pickedColor3 = preferences.getInt(PICKED_COLOR_KEY3, ContextCompat.getColor(getApplicationContext(), R.color.transparent));
        pickedColor4 = preferences.getInt(PICKED_COLOR_KEY4, ContextCompat.getColor(getApplicationContext(), R.color.overlay_fg_20));

        curtain = findViewById(R.id.constraintLayout);
        curtain.setAlpha(0f);
        fab = findViewById(R.id.fab);
        fab.setAlpha(0f);
        switchcard = findViewById(R.id.switchcard);
        switchcard.setAlpha(0f);
        myswitch = findViewById(R.id.myswitch);
        myswitch.setAlpha(0f);
        navigation = findViewById(R.id.bottom_navigation);
        navigation.setAlpha(0f);

        new CountDownTimer(900, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {  }
            @Override
            public void onFinish() {

                // you cannot touch the UI from another thread. This thread now calls a function on the main thread
                loadFragment(new fragment_1());
                curtain.animate().alpha(1f).setDuration(100).setStartDelay(0).start();
                // ini & setup Animations
                myswitch.animate().alpha(1f).setDuration(600).setStartDelay(300);
                switchcard.animate().alpha(1f).setDuration(500).setStartDelay(300);
                moveToTop(navigation,70,200,3.0f);
                moveToTop(fab,100,400,2.0f);
                PermissionHelper.checkPermissions(MainActivity.this);
            }
        }.start();
        //loading the default fragment


        // dark/light mode switch
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

        //getting bottom navigation view and attaching the listener
        navigation.setOnNavigationItemSelectedListener(this);

        LottieAnimationView intro = findViewById(R.id.intro);
        intro.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                LottieAnimationView intro = findViewById(R.id.intro);
                intro.animate().alpha(0f).setDuration(100).setStartDelay(0).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
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
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
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
            case DIALOG_ID1:
                try {
                    //createColorBitmapAndSave(1366, 768, color);
                    preferences.edit().putInt(PICKED_COLOR_KEY1, color).apply();
                    Fragment fragment = new fragment_2();
                    loadFragment(fragment);

                } catch (Throwable t) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error on ID1.", Toast.LENGTH_LONG );
                    toast.show();
                }
                break;
            case DIALOG_ID2:
                try {
                    //fab.setBackgroundTintList(ColorStateList.valueOf(color));
                    preferences.edit().putInt(PICKED_COLOR_KEY2, color).apply();
                    preferences.edit().putInt(PICKED_COLOR_KEY3, color).apply();
                    preferences.edit().putInt(PICKED_COLOR_KEY4, ContextCompat.getColor(getApplicationContext(), R.color.transparent)).apply();
                    Fragment fragment = new fragment_2();
                    loadFragment(fragment);


                } catch (Throwable t) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Error on ID2", Toast.LENGTH_LONG);
                    toast.show();
                }
                break;
        }
    }

    @Override
    public void onDialogDismissed(int dialogId) {
    }

    public void restartApp() {
        //Intent b = new Intent(getApplicationContext(), MainActivity.class);
        //finish();
        //startActivity(b);
        this.recreate();
        curtain.setAlpha(0f);
    }

    public static void createColorBitmapAndSave(int width, int height, @ColorInt int pickedColor1, @ColorInt int pickedColor2)
            throws IOException {
        //Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //bitmap.eraseColor(color);
        int colors[] = new int[2];
        colors[0] = Color.parseColor("#000000");
        colors[1] = Color.parseColor("#123456");

        LinearGradient gradient = new LinearGradient(0, 0, 0, height, pickedColor1,pickedColor2, Shader.TileMode.REPEAT);
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

        File file = new File(parent, "LINEAR_#" + Integer.toHexString(pickedColor1) + "_" + "#" + Integer.toHexString(pickedColor2) + ".png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Use Bitmap.CompressFormat.JPEG if you want JPEG
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
    }
    public static void createLottieBitmapAndSave(int width, int height, Drawable drawable, @ColorInt int pickedColor1, @ColorInt int pickedColor2)
            throws IOException {

        Paint p = new Paint();
        p.setDither(true);
        p.setAntiAlias(true);

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(new RectF(0, 0, width, height), p);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);

        File parent = new File(Environment.getExternalStorageDirectory() + "/"
                + DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/"));
        if (!parent.exists() && !parent.mkdirs()) {
            throw new IOException("failed to create path " + parent);
        }

        File file = new File(parent, "RADIAL_#" + Integer.toHexString(pickedColor1) + "_" + "#" + Integer.toHexString(pickedColor2) + ".png");
        try (FileOutputStream fos = new FileOutputStream(file)) {
            // Use Bitmap.CompressFormat.JPEG if you want JPEG
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        }
    }
    public void substratum(View view) { ////button to launch theme on substratum app
        {
            Intent intent = new Intent();
            intent = intent.setClassName("projekt.substratum",
                    "projekt.substratum.MainActivity");
            startActivity(intent);
        }

    }
    public void launchTelegramLink(View view) {   ////button to launch a telegram group
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/moelle1")); ////Insert your link
        startActivity(telegramLink);
    }
    public void launchEmail(View view) { ////button to launch paypal.me donationslink
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:manuel.moellmann@gmail.com")); ////Insert your link
        startActivity(telegramLink);
    }
    public void launchPaypal(View view) { ////button to launch paypal.me donationslink
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/moelle89")); ////Insert your link
        startActivity(telegramLink);
    }
}
