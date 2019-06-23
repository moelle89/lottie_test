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
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.moelle.deepdarkness.R;

import static com.jaredrummler.android.colorpicker.ColorPickerDialog.newBuilder;
import static com.moelle.deepdarkness.AnimationPack.fadeIn;
import static com.moelle.deepdarkness.AnimationPack.moveToBottom;
import static com.moelle.deepdarkness.AnimationPack.moveToTop;
import static com.moelle.deepdarkness.MainActivity.DD_Colors;
import static com.moelle.deepdarkness.MainActivity.createColorBitmapAndSave;
import static com.moelle.deepdarkness.MainActivity.createLottieBitmapAndSave;

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

    public static final String TAG = fragment_2.class.getSimpleName();

    private static final int DIALOG_ID1 = 0;
    private static final int DIALOG_ID2 = 1;

    private LottieAnimationView keyboard2, radial_gradient, dashboard_head, keyboard, dialogbg, dialogbg0;
    private ImageView iconMAIL, imageView, dlIcon;
    private View center, dialogView;
    private CardView mail, card1, CardView2, CardView3, CardView4;
    private Button  dl_btn;
    private TextView cat_top1, cat_top2, cat_bottom, dlText, infoText;

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
        View v = inflater.inflate(R.layout.fragment_2, null);

        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        pickedColor1 = preferences.getInt(PICKED_COLOR_KEY1, ContextCompat.getColor(v.getContext(), R.color.colorAccent));
        pickedColor2 = preferences.getInt(PICKED_COLOR_KEY2, ContextCompat.getColor(v.getContext(), R.color.accent14));
        pickedColor3 = preferences.getInt(PICKED_COLOR_KEY3, ContextCompat.getColor(v.getContext(), R.color.accent14));
        pickedColor4 = preferences.getInt(PICKED_COLOR_KEY4, ContextCompat.getColor(v.getContext(), R.color.transparent));


        LinearLayout card2 = v.findViewById(R.id.accent4);
        radial_gradient = v.findViewById(R.id.radial_gradient);
        radial_gradient.addValueCallback(
                new KeyPath("FG", "FG", "**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor1, PorterDuff.Mode.SRC_IN);
                    }});
        radial_gradient.addValueCallback(
                new KeyPath("FG", "BG", "**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor2, PorterDuff.Mode.SRC_IN);
                    }});
        radial_gradient.addValueCallback(
                new KeyPath("BG", "**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor2, PorterDuff.Mode.SRC_IN);
                    }});

        new CountDownTimer(250, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // do something after 1s
            }
            @Override
            public void onFinish() {
                radial_gradient.playAnimation();
            }
        }.start();
        card2.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        card2.setForegroundTintList(ColorStateList.valueOf(pickedColor2));
        card1 = v.findViewById(R.id.CardView1);
        card1.setBackgroundTintList(ColorStateList.valueOf(pickedColor1));
        center = v.findViewById(R.id.center);

        CardView2 = v.findViewById(R.id.CardView2);
        CardView3 = v.findViewById(R.id.CardView3);
        CardView4 = v.findViewById(R.id.CardView4);
        dlIcon = v.findViewById(R.id.dlIcon);
        dlText = v.findViewById(R.id.dlText);
        CardView background1 = v.findViewById(R.id.background1);
        CardView background2 = v.findViewById(R.id.background2);
        CardView background3 = v.findViewById(R.id.background3);
        CardView background4 = v.findViewById(R.id.background4);

        cat_top1 = v.findViewById(R.id.textViewTitle);
        cat_top2 = v.findViewById(R.id.textViewSubtitle);
        cat_bottom = v.findViewById(R.id.textViewBottom);
        keyboard = v.findViewById(R.id.dashboard_head);
        keyboard.setAnimation(R.raw.keyboard);

        final int fg = ContextCompat.getColor(v.getContext(), R.color.textColor);
        final int bg = ContextCompat.getColor(v.getContext(), R.color.background);
        final int stroke = ContextCompat.getColor(v.getContext(), R.color.overlay_fg_15);
        final int stroke2 = pickedColor4;

        keyboard.addValueCallback(
                new KeyPath("BG2","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor1, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard.addValueCallback(
                new KeyPath("BG2","STROKE","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(stroke, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard.addValueCallback(
                new KeyPath("BG1","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor2, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard.addValueCallback(
                new KeyPath("BG1FG","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(pickedColor3, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard.addValueCallback(
                new KeyPath("BG1STROKE","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(stroke2, PorterDuff.Mode.SRC_IN);
                    }});


        keyboard.addValueCallback(
                new KeyPath("GBOARD", "fg", "**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(fg, PorterDuff.Mode.SRC_IN);
                    }});

        keyboard.addValueCallback(
                new KeyPath("GBOARD", "bg", "**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter(bg, PorterDuff.Mode.SRC_IN);
                    }});

        // ini Animations
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

        fadeIn(keyboard, 100);
        fadeIn(cat_top1, 100);
        fadeIn(cat_top2, 200);
        fadeIn(cat_bottom, 300);
        fadeIn(card2, 100);

        moveToBottom(card1,1f, 80,0,1);
        moveToBottom(CardView2,1f,80,150,1);
        moveToTop(CardView3,80,200,1);
        moveToTop(CardView4,80,250,1);
        moveToBottom(dlIcon,0.2f, -80,300,2);

        moveToTop(background1,80,200,1);
        moveToTop(background2,70,250,2);
        moveToTop(background3,70,300, 2);
        moveToTop(background4,60,350,3);


        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        CardView3.setOnClickListener(this);
        CardView4.setOnClickListener(this);
        background1.setOnClickListener(this);
        background2.setOnClickListener(this);
        background3.setOnClickListener(this);
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
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.circle_selected );
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
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.circle_selected );
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
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.circle_selected );
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
        final int pickedColor4 = ContextCompat.getColor(getContext(), R.color.circle_selected );
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
        ColorPickerDialog pickerDialog1 = new ColorPickerDialog();

        pickerDialog1.newBuilder()
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
        dialogView = View.inflate(getActivity(), R.layout.dialog_gboard, null);
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
        infoText = dialog.findViewById(R.id.infoText);
        iconMAIL = dialog.findViewById(R.id.iconMAIL);
        mail = dialog.findViewById(R.id.mail);
        dl_btn = dialog.findViewById(R.id.dl_btn);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                revealShow(dialogView, false, dialog);
            }
        });
        dl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createColorBitmapAndSave(1680, 1050, pickedColor1, pickedColor2);
                    Drawable mDrawable = radial_gradient.getDrawable();
                    final int width = mDrawable.getBounds().width();
                    final int height = mDrawable.getBounds().height();

                    createLottieBitmapAndSave(width,height,mDrawable, pickedColor1, pickedColor2);

                    Toast toast = new Toast(getContext());
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_toast,null);
                    CardView card = view.findViewById(R.id.card_toast);
                    card.setCardBackgroundColor(pickedColor1);
                    TextView textView = view.findViewById(R.id.text);
                    textView.setText(R.string.success);
                    toast.setView(view);
                    toast.setGravity(Gravity.BOTTOM, 0, 255| Gravity.BOTTOM);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();

                    moveToTop(view,80,450,3);
                    revealShow(dialogView, false, dialog);
                } catch (Throwable t) {
                    Toast toast = Toast.makeText(getContext(), "Something went wrong.", Toast.LENGTH_SHORT );
                    toast.show();
                }
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
        dlIcon.animate().translationY(200).alpha(0f).setStartDelay(200).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
        dlText.animate().translationY(50).scaleY(0.8f).alpha(0f).setStartDelay(80).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
        dl_btn.setBackgroundTintList(ColorStateList.valueOf(pickedColor2));
        dl_btn.setAlpha(0.0f);
        dl_btn.setScaleX(0.7f);
        dl_btn.setScaleY(0.7f);
        dl_btn.setTranslationY(350);
        dl_btn.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(450).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
        infoText.setAlpha(0.0f);
        infoText.setTranslationY(50);
        infoText.animate().translationY(0).alpha(0.9f).setStartDelay(600).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
       mail.setAlpha(0.0f);
        mail.setScaleX(0.7f);
        mail.setScaleY(0.7f);
        mail.setTranslationY(200);
        mail.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(550).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        iconMAIL.setAlpha(0f);
        iconMAIL.setScaleX(0.7f);
        iconMAIL.setScaleY(0.7f);
        iconMAIL.setTranslationY(200);
        iconMAIL.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(750).setDuration(550).setInterpolator(new FastOutSlowInInterpolator()).start();
        keyboard2.setAlpha(0f);
        keyboard2.setTranslationX(550);
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setRotation(120);
        imageView.animate().rotation(0).scaleX(1).scaleY(1).alpha(1.0f).setStartDelay(550).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();
    }
    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView;

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (center.getX() + (center.getWidth() / 2));
        int cy = (int) (center.getY()) + center.getHeight();


        if (b) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, endRadius);

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
            mail.animate().translationY(100).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(80).setDuration(550).setInterpolator(new FastOutSlowInInterpolator()).start();
            iconMAIL.animate().translationY(200).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(200).setDuration(400).setInterpolator(new FastOutSlowInInterpolator()).start();
            keyboard2.animate().translationX(550).alpha(0f).setStartDelay(0).setDuration(550).setInterpolator(new FastOutSlowInInterpolator()).start();
            imageView.animate().rotation(120).scaleX(0.5f).scaleY(0.5f).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            dl_btn.animate().translationY(100).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(80).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            infoText.animate().translationY(100).alpha(0f).setStartDelay(80).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            moveToBottom(dlIcon,0.2f,-80,800,3);
            moveToBottom(dlText,1f,-80,550,1);
            anim.setDuration(750);
            anim.setStartDelay(100);
            anim.start();

        }
    }
    private void revealShowDelayed() {
{
        new CountDownTimer(125, 1000) {
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
}}