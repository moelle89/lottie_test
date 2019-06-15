package com.moelle.deepdarkness.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.LottieValueCallback;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.moelle.deepdarkness.R;

import static com.jaredrummler.android.colorpicker.ColorPickerDialog.newBuilder;
import static com.moelle.deepdarkness.AnimationPack.scaleIn;
import static com.moelle.deepdarkness.AnimationPack.scaleOut;
import static com.moelle.deepdarkness.MainActivity.DD_Colors;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment implements View.OnClickListener {

    private SharedPreferences preferences;
    private int pickedColor1;
    private int pickedColor2;
    private int pickedColor3;
    private int pickedColor4;
    private final String PICKED_COLOR_KEY1 = "picker-key1";
    private final String PICKED_COLOR_KEY2 = "picker-key2";
    private final String PICKED_COLOR_KEY3 = "picker-key3";
    private final String PICKED_COLOR_KEY4 = "picker-key4";

    private int pickedAnimation;
    private final String PICKED_ANIMATION_KEY = "picker-key4";

    public static final String TAG = fragment_2.class.getSimpleName();

    private static final int DIALOG_ID1 = 0;
    private static final int DIALOG_ID2 = 1;

    private LottieAnimationView keyboard2, dashboard_head, keyboard, dialogbg, dialogbg0;
    private ImageView iconMAIL, imageView;
    private View center;
    private CardView mail, card1, CardView2, CardView3, CardView4;
    private TextView cat_top1, cat_top2, cat_bottom;
    // image url to download
    private static final String accent6 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/6.png";

    public fragment_2() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.transparent));
        View v = inflater.inflate(R.layout.fragment_2test, null);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        pickedColor1 = preferences.getInt(PICKED_COLOR_KEY1, ContextCompat.getColor(getContext(), R.color.colorAccent));
        pickedColor2 = preferences.getInt(PICKED_COLOR_KEY2, ContextCompat.getColor(getContext(), R.color.accent14));
        pickedColor3 = preferences.getInt(PICKED_COLOR_KEY3, ContextCompat.getColor(getContext(), R.color.accent14));
        pickedColor4 = preferences.getInt(PICKED_COLOR_KEY4, ContextCompat.getColor(getContext(), R.color.overlay_fg_30));


        LinearLayout card2 = v.findViewById(R.id.accent4);
        LottieAnimationView radial_gradient = v.findViewById(R.id.radial_gradient);
        radial_gradient.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        SimpleColorFilter tintfilter = new SimpleColorFilter(pickedColor1);
        KeyPath keyfg = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(tintfilter);
        radial_gradient.addValueCallback(keyfg, LottieProperty.COLOR_FILTER, callback);
        radial_gradient.playAnimation();
        card2.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        card2.setForegroundTintList(ColorStateList.valueOf(pickedColor2));
        card1 = v.findViewById(R.id.CardView1);
        card1.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        center = v.findViewById(R.id.center);

        CardView2 = v.findViewById(R.id.CardView2);
        CardView3 = v.findViewById(R.id.CardView3);
        CardView4 = v.findViewById(R.id.CardView4);
        CardView background1 = v.findViewById(R.id.background1);
        CardView background2 = v.findViewById(R.id.background2);
        CardView background3 = v.findViewById(R.id.background3);
        CardView background4 = v.findViewById(R.id.background4);

        cat_top1 = v.findViewById(R.id.textViewTitle);
        cat_top2 = v.findViewById(R.id.textViewSubtitle);
        cat_bottom = v.findViewById(R.id.textViewBottom);
        cat_top1.setAlpha(0f);
        cat_top2.setAlpha(0f);
        cat_bottom.setAlpha(0f);
        keyboard = v.findViewById(R.id.dashboard_head);
        keyboard.setAnimation(R.raw.keyboard);

        final int fg = ContextCompat.getColor(v.getContext(), R.color.textColor);
        final int bg = ContextCompat.getColor(v.getContext(), R.color.background);
        final int stroke = ContextCompat.getColor(v.getContext(), R.color.overlay_fg_20);

        SimpleColorFilter keyboardtint = new SimpleColorFilter(pickedColor1);
        KeyPath keyfg3 = new KeyPath("BG2","**");
        LottieValueCallback<ColorFilter> callback3 = new LottieValueCallback<ColorFilter>(keyboardtint);
        keyboard.addValueCallback(keyfg3, LottieProperty.COLOR_FILTER, callback3);

        com.moelle.deepdarkness.SimpleColorFilter BG2STROKE = new com.moelle.deepdarkness.SimpleColorFilter(stroke, PorterDuff.Mode.SRC_IN);
        KeyPath KEYBG2STROKE = new KeyPath("BG2","STROKE","**");
        LottieValueCallback<ColorFilter> callback7 = new LottieValueCallback<ColorFilter>(BG2STROKE);
        keyboard.addValueCallback(KEYBG2STROKE, LottieProperty.COLOR_FILTER, callback7);

        KeyPath KEYBG3STROKE = new KeyPath("STROKE","**");
        keyboard.addValueCallback(KEYBG3STROKE, LottieProperty.COLOR_FILTER, callback7);

        SimpleColorFilter keyboardtint2 = new SimpleColorFilter(pickedColor2);
        KeyPath keyfg4 = new KeyPath("BG1","**");
        LottieValueCallback<ColorFilter> callback4 = new LottieValueCallback<ColorFilter>(keyboardtint2);
        keyboard.addValueCallback(keyfg4, LottieProperty.COLOR_FILTER, callback4);

        com.moelle.deepdarkness.SimpleColorFilter keyboardtint3 = new com.moelle.deepdarkness.SimpleColorFilter(pickedColor3, PorterDuff.Mode.SRC_IN);
        KeyPath keyfg5 = new KeyPath("BG1FG","**");
        LottieValueCallback<ColorFilter> callback5 = new LottieValueCallback<ColorFilter>(keyboardtint3);
        keyboard.addValueCallback(keyfg5, LottieProperty.COLOR_FILTER, callback5);

        com.moelle.deepdarkness.SimpleColorFilter keyboardtint5 = new com.moelle.deepdarkness.SimpleColorFilter(pickedColor4, PorterDuff.Mode.SRC_IN);
        KeyPath keyfg7 = new KeyPath("BG1STROKE","**");
        LottieValueCallback<ColorFilter> callback8 = new LottieValueCallback<ColorFilter>(keyboardtint5);
        keyboard.addValueCallback(keyfg7, LottieProperty.COLOR_FILTER, callback8);

        com.moelle.deepdarkness.SimpleColorFilter keyboardtint4 = new com.moelle.deepdarkness.SimpleColorFilter(stroke, PorterDuff.Mode.SRC_IN);
        KeyPath keyfg6 = new KeyPath("BG2","BG2","BG2","STROKE","**");
        LottieValueCallback<ColorFilter> callback6 = new LottieValueCallback<ColorFilter>(keyboardtint4);
        keyboard.addValueCallback(keyfg6, LottieProperty.COLOR_FILTER, callback6);

        com.moelle.deepdarkness.SimpleColorFilter filterfg = new com.moelle.deepdarkness.SimpleColorFilter(fg, PorterDuff.Mode.SRC_IN);
        KeyPath keyboardKey = new KeyPath("GBOARD", "fg", "**");
        LottieValueCallback<ColorFilter> keyboardCall = new LottieValueCallback<ColorFilter>(filterfg);
        keyboard.addValueCallback(keyboardKey, LottieProperty.COLOR_FILTER, keyboardCall);
        SimpleColorFilter filterbg = new SimpleColorFilter(bg);
        KeyPath keybg = new KeyPath("GBOARD", "bg", "**");
        LottieValueCallback<ColorFilter> callback2 = new LottieValueCallback<ColorFilter>(filterbg);
        keyboard.addValueCallback(keybg, LottieProperty.COLOR_FILTER, callback2);
        // ini Animations
        keyboard.setAlpha(0f);
        keyboard.animate().alpha(1f).setDuration(600).setStartDelay(100);
        Animation vonOben = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        Animation vonOben2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben2.setStartOffset(70);
        Animation vonOben3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben3.setStartOffset(170);
        Animation vonOben4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben4.setStartOffset(220);
        Animation vonUnten = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten.setStartOffset(170);
        Animation vonUnten2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten2.setStartOffset(220);
        Animation vonUnten3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten3.setStartOffset(250);
        Animation vonUnten4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten4.setStartOffset(280);
        cat_top1.animate().alpha(1f).setDuration(1000).setStartDelay(200);
        cat_top2.animate().alpha(1f).setDuration(1000).setStartDelay(300);
        cat_bottom.animate().alpha(1f).setDuration(1000).setStartDelay(400);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        CardView3.setOnClickListener(this);
        CardView4.setOnClickListener(this);
        card2.setAlpha(0f);
        card2.animate().alpha(1f).setDuration(600).setStartDelay(100);
        card1.setAnimation(vonOben);
        CardView2.setAnimation(vonOben2);
        CardView3.setAnimation(vonUnten);
        CardView4.setAnimation(vonUnten2);
        background1.setAnimation(vonUnten);
        background1.setOnClickListener(this);
        background2.setAnimation(vonUnten2);
        background2.setOnClickListener(this);
        background3.setAnimation(vonUnten3);
        background3.setOnClickListener(this);
        background4.setAnimation(vonUnten4);
        background4.setOnClickListener(this);

        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CardView1: {
                //getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                launchPicker1(getView());
                break;
            }
            case R.id.accent4: {
                launchPicker2(getView());
                break;
            }
            case R.id.CardView4: {
                showContact();
                break;
            }
            case R.id.background1: {
                setBackground1();
                break;
            }
            case R.id.background2: {
                setBackground2();
                break;
            }
            case R.id.background3: {
                setBackground3();
                break;
            }
            case R.id.background4: {
                setBackground4();
                break;
            }
        }
    }

    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void setBackground1() {
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.overlay_fg_20 );
        final int pickedColor3 = ContextCompat.getColor(getContext(), R.color.transparent );
        final int pickedColor2 = ContextCompat.getColor(getContext(), R.color.background1 );
        final int pickedColor1 = ContextCompat.getColor(getContext(), R.color.background1 );
        preferences.edit().putInt(PICKED_COLOR_KEY4, pickedColor4).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY3, pickedColor3).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY2, pickedColor2).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY1, pickedColor1).apply();
        Fragment fragment = new fragment_2();
        loadFragment(fragment);
    }
    public void setBackground2() {
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.overlay_fg_20 );
        final int pickedColor3 = ContextCompat.getColor(getContext(), R.color.transparent );
        final int pickedColor2 = ContextCompat.getColor(getContext(), R.color.background2 );
        final int pickedColor1 = ContextCompat.getColor(getContext(), R.color.background2 );
        preferences.edit().putInt(PICKED_COLOR_KEY4, pickedColor4).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY3, pickedColor3).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY2, pickedColor2).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY1, pickedColor1).apply();
        Fragment fragment = new fragment_2();
        loadFragment(fragment);
    }
    public void setBackground3() {
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.overlay_fg_20 );
        final int pickedColor3 = ContextCompat.getColor(getContext(), R.color.transparent );
        final int pickedColor2 = ContextCompat.getColor(getContext(), R.color.background3 );
        final int pickedColor1 = ContextCompat.getColor(getContext(), R.color.background3 );
        preferences.edit().putInt(PICKED_COLOR_KEY4, pickedColor4).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY3, pickedColor3).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY2, pickedColor2).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY1, pickedColor1).apply();
        Fragment fragment = new fragment_2();
        loadFragment(fragment);
    }

    public void setBackground4() {
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.overlay_fg_20 );
        final int pickedColor3 = ContextCompat.getColor(getContext(), R.color.transparent );
        final int pickedColor2 = ContextCompat.getColor(getContext(), R.color.background4 );
        final int pickedColor1 = ContextCompat.getColor(getContext(), R.color.background4 );
        preferences.edit().putInt(PICKED_COLOR_KEY4, pickedColor4).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY3, pickedColor3).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY2, pickedColor2).apply();
        preferences.edit().putInt(PICKED_COLOR_KEY1, pickedColor1).apply();
        Fragment fragment = new fragment_2();
        loadFragment(fragment);
    }

    public void launchPicker1(View view) {
        final int Default = preferences.getInt(PICKED_COLOR_KEY1, ContextCompat.getColor(view.getContext(), R.color.accent0));
        ColorPickerDialog.newBuilder()
                .setDialogTitle(R.string.pickerTitle)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setColor(Default)
                .setPresets(DD_Colors)
                .setAllowPresets(true)
                .setDialogId(DIALOG_ID1)
                .setShowColorShades(true)
                .setAllowCustom(true)
                .setShowAlphaSlider(false)
                .show(getActivity());

    }

    public void launchPicker2(View view) {
        final int Default2 = preferences.getInt(PICKED_COLOR_KEY2, ContextCompat.getColor(view.getContext(), R.color.accent14));
        newBuilder()
                .setDialogTitle(R.string.pickerTitle2)
                .setDialogType(ColorPickerDialog.TYPE_PRESETS)
                .setColor(Default2)
                .setPresets(DD_Colors)
                .setAllowPresets(true)
                .setDialogId(DIALOG_ID2)
                .setShowColorShades(true)
                .setAllowCustom(true)
                .setShowAlphaSlider(false)
                .show(getActivity());
    }

    private void showContact() {
        final View dialogView = View.inflate(getActivity(), R.layout.dialog_gboard, null);
        final Dialog dialog = new Dialog(getActivity(), R.style.ApptThemeDialogContact);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        dialog.getWindow().setStatusBarColor(Color.TRANSPARENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        dialogbg0 = dialog.findViewById(R.id.dialogbg0);
        dialogbg0.addValueCallback(
                new KeyPath("circEND","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(getResources().getColor(R.color.dialog_contact2), PorterDuff.Mode.SRC_IN);
                    }});
        dialogbg0.addValueCallback(
                new KeyPath("circSTART","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor2, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard2 = dialog.findViewById(R.id.dashboard_head2);
        keyboard2.addValueCallback(
                new KeyPath("BG2","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor1, PorterDuff.Mode.SRC_IN);
                    }});
        keyboard2.addValueCallback(
                new KeyPath("BG1","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor2, PorterDuff.Mode.SRC_IN);
                    }});
        keyboard2.addValueCallback(
                new KeyPath("fg","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(getResources().getColor(R.color.secondary_text_light), PorterDuff.Mode.SRC_IN);
                    }});

        imageView = dialog.findViewById(R.id.closeDialogImg);
        iconMAIL = dialog.findViewById(R.id.iconMAIL);
        mail = dialog.findViewById(R.id.mail);

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
        revealShowDelayed();

        mail.setAlpha(0.0f);
        mail.setScaleX(0.7f);
        mail.setScaleY(0.7f);
        mail.setTranslationY(200);
        mail.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(550).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        iconMAIL.setAlpha(0f);
        iconMAIL.setScaleX(0.7f);
        iconMAIL.setScaleY(0.7f);
        iconMAIL.setTranslationY(50);
        iconMAIL.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(700).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        keyboard2.setAlpha(0f);
        keyboard2.setTranslationX(550);
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setRotation(120);
        imageView.animate().rotation(0).scaleX(1).scaleY(1).alpha(0.8f).setStartDelay(550).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog_gboard);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (center.getX() + (center.getWidth() / 2));
        int cy = (int) (center.getY()) + center.getHeight();


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, endRadius);

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
                    view.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                }
            });
            mail.animate().translationY(200).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(80).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            iconMAIL.animate().translationY(50).scaleX(0.7f).scaleY(0.7f).alpha(0f).setStartDelay(180).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            keyboard2.animate().translationX(550).alpha(0f).setStartDelay(0).setDuration(550).setInterpolator(new FastOutSlowInInterpolator()).start();
            imageView.animate().rotation(120).scaleX(0.5f).scaleY(0.5f).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            anim.setDuration(750);
            anim.setStartDelay(100);
            anim.start();

        }
    }
    private void revealShowDelayed() {

        new CountDownTimer(250, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // do something after 1s
            }
            @Override
            public void onFinish() {
                // you cannot touch the UI from another thread. This thread now calls a function on the main thread
                keyboard2.animate().translationX(0).alpha(1f).setStartDelay(200).setDuration(650).setInterpolator(new FastOutLinearInInterpolator()).start();
                keyboard2.playAnimation();
            }
        }.start(); }
}