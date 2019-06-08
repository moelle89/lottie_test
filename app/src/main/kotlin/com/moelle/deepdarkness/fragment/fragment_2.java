package com.moelle.deepdarkness.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.moelle.deepdarkness.R;

import static com.moelle.deepdarkness.MainActivity.DD_Colors;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment implements View.OnClickListener {

    private SharedPreferences preferences;
    private int pickedColor1;
    private int pickedColor2;
    private final String PICKED_COLOR_KEY1 = "picker-key1";
    private final String PICKED_COLOR_KEY2 = "picker-key2";
    public static final String TAG = fragment_2.class.getSimpleName();

    private static final int DIALOG_ID1 = 0;
    private static final int DIALOG_ID2 = 1;

    private LottieAnimationView keyboard2;
    private ImageView closeBG, iconTG, iconMAIL, imageView;
    private CardView tg, mail;
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
        CardView card1 = v.findViewById(R.id.CardView1);
        card1.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));

        CardView CardView2 = v.findViewById(R.id.CardView2);
        CardView CardView3 = v.findViewById(R.id.CardView3);
        CardView CardView4 = v.findViewById(R.id.CardView4);
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

        LottieAnimationView keyboard = v.findViewById(R.id.dashboard_head);
        final int fg = ContextCompat.getColor(v.getContext(), R.color.textColor);
        final int bg = ContextCompat.getColor(v.getContext(), R.color.background);
        final int black = ContextCompat.getColor(v.getContext(), R.color.md_black);

        keyboard.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        SimpleColorFilter keyboardtint = new SimpleColorFilter(pickedColor1);
        KeyPath keyfg3 = new KeyPath("BG2","**");
        LottieValueCallback<ColorFilter> callback3 = new LottieValueCallback<ColorFilter>(keyboardtint);
        keyboard.addValueCallback(keyfg3, LottieProperty.COLOR_FILTER, callback3);

        keyboard.setBackgroundTintList(ColorStateList.valueOf(pickedColor2));
        SimpleColorFilter keyboardtint2 = new SimpleColorFilter(pickedColor2);
        KeyPath keyfg4 = new KeyPath("BG1","**");
        LottieValueCallback<ColorFilter> callback4 = new LottieValueCallback<ColorFilter>(keyboardtint2);
        keyboard.addValueCallback(keyfg4, LottieProperty.COLOR_FILTER, callback4);

        SimpleColorFilter filterfg = new SimpleColorFilter(fg);
        KeyPath keyboardKey = new KeyPath("fg", "**");
        LottieValueCallback<ColorFilter> keyboardCall = new LottieValueCallback<ColorFilter>(filterfg);
        keyboard.addValueCallback(keyboardKey, LottieProperty.COLOR_FILTER, keyboardCall);
        SimpleColorFilter filterbg = new SimpleColorFilter(bg);
        KeyPath keybg = new KeyPath("bg", "**");
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
        CardView4.setOnClickListener(this);
        card2.setAlpha(0f);
        card2.animate().alpha(1f).setDuration(600).setStartDelay(100);
        card1.setAnimation(vonOben);
        CardView2.setAnimation(vonOben2);
        CardView3.setAnimation(vonUnten);
        CardView4.setAnimation(vonUnten2);
        background1.setAnimation(vonUnten);
        background2.setAnimation(vonUnten2);
        background3.setAnimation(vonUnten3);
        background4.setAnimation(vonUnten4);
        
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
        }
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
        ColorPickerDialog.newBuilder()
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
        dialog.setContentView(dialogView);

        keyboard2 = dialog.findViewById(R.id.dashboard_head2);

        keyboard2.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        SimpleColorFilter keyboardtint = new SimpleColorFilter(pickedColor1);
        KeyPath keyfg3 = new KeyPath("BG2","**");
        LottieValueCallback<ColorFilter> callback3 = new LottieValueCallback<ColorFilter>(keyboardtint);
        keyboard2.addValueCallback(keyfg3, LottieProperty.COLOR_FILTER, callback3);

        final int fg = ContextCompat.getColor(dialog.getContext(), R.color.textColor);
        SimpleColorFilter filterfg = new SimpleColorFilter(fg);
        KeyPath keyboardKey = new KeyPath("fg", "**");
        LottieValueCallback<ColorFilter> keyboardCall = new LottieValueCallback<ColorFilter>(filterfg);
        keyboard2.addValueCallback(keyboardKey, LottieProperty.COLOR_FILTER, keyboardCall);


        keyboard2.setBackgroundTintList(ColorStateList.valueOf(pickedColor2));
        SimpleColorFilter keyboardtint2 = new SimpleColorFilter(pickedColor2);
        KeyPath keyfg4 = new KeyPath("BG1","**");
        LottieValueCallback<ColorFilter> callback4 = new LottieValueCallback<ColorFilter>(keyboardtint2);
        keyboard2.addValueCallback(keyfg4, LottieProperty.COLOR_FILTER, callback4);


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

        mail.setAlpha(0.0f);
        mail.setScaleX(0.7f);
        mail.setScaleY(0.7f);
        mail.setTranslationY(200);
        mail.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(550).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        iconMAIL.setAlpha(0.0f);
        iconMAIL.setScaleX(0.8f);
        iconMAIL.setScaleY(0.8f);
        iconMAIL.setTranslationY(250);
        iconMAIL.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(750).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        keyboard2.setAlpha(0.0f);
        keyboard2.setTranslationX(200);
        keyboard2.animate().translationX(0).alpha(1f).setStartDelay(0).setDuration(750).setInterpolator(new AccelerateInterpolator()).start();
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

        int cx = (int) (cat_top1.getX() + (cat_top1.getWidth() / 2));
        int cy = (int) (cat_top1.getY()) + cat_top1.getHeight() - 90;


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 80, endRadius);

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
                    view.setVisibility(View.INVISIBLE);
                    dialog.dismiss();
                }
            });
            mail.animate().translationY(200).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(80).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            iconMAIL.animate().translationY(200).scaleX(0.7f).scaleY(0.7f).alpha(0f).setStartDelay(180).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            keyboard2.animate().translationX(200).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new AccelerateInterpolator()).start();
            imageView.animate().rotation(120).scaleX(0.5f).scaleY(0.5f).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            anim.setDuration(750);
            anim.setStartDelay(100);
            anim.start();

        }
    }
}
