package com.moelle.deepdarkness.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.transition.Transition;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.moelle.deepdarkness.LottieIntro;
import com.moelle.deepdarkness.R;
import com.moelle.deepdarkness.Wallpaper;

import static androidx.core.content.ContextCompat.getColor;
import static com.moelle.deepdarkness.AnimationPack.fadeIn;
import static com.moelle.deepdarkness.AnimationPack.moveToBottom;
import static com.moelle.deepdarkness.AnimationPack.moveToTop;
import static com.moelle.deepdarkness.AnimationPack.scaleIn;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_1 extends Fragment implements View.OnClickListener {

    private LinearLayout cat_top, cat_middle;
    private ImageView closeBG, iconTG, iconMAIL,animation_view2 ,imageView;
    private LottieAnimationView dialogbg0;
    private CardView cardRight, cardLeft, cardRight2, cardLeft2, card4, card5, card6, tg, mail;
    private FrameLayout cardTop, flmiddle;
    private LinearLayout anchor_cardleft;
    private Transition explode;

    public static final String TAG = fragment_1.class.getSimpleName();
    // Give your color picker dialog unique IDs if you have multiple dialogs.
    private static final int DIALOG_ID = 0;

    public fragment_1() {
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
        getActivity().getWindow().setStatusBarColor(getColor(getContext(),R.color.transparent));
        View v = inflater.inflate(R.layout.fragment_1, null);

        // content ini
        cardTop = v.findViewById(R.id.flmiddle);
        animation_view2 = v.findViewById(R.id.animation_view2);

        cardRight = v.findViewById(R.id.cardRight);
        cardRight2 = v.findViewById(R.id.cardRight2);
        cardLeft = v.findViewById(R.id.cardLeft);
        cardLeft2 = v.findViewById(R.id.cardLeft2);
        cat_top = v.findViewById(R.id.cat_top);
        cat_middle = v.findViewById(R.id.cat_middle);
        anchor_cardleft = v.findViewById(R.id.anchor_cardleft);
        card4 = v.findViewById(R.id.card4);
        card5 = v.findViewById(R.id.card5);
        card6 = v.findViewById(R.id.card6);
        cardLeft.setOnClickListener(this);
        cardRight.setOnClickListener(this);
        cardLeft2.setOnClickListener(this);
        cardRight2.setOnClickListener(this);
        card5.setOnClickListener(this);

        // ini Animations
        Animation vonOben2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_top_to_bottom);
        vonOben2.setStartOffset(70);
        Animation vonUnten = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten.setStartOffset(170);
        Animation vonUnten2 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten2.setStartOffset(220);
        Animation vonUnten3 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten3.setStartOffset(250);
        Animation vonUnten4 = AnimationUtils.loadAnimation(getActivity(), R.anim.anime_bottom_to_top);
        vonUnten4.setStartOffset(280);

        // setup Animation :
        scaleIn(animation_view2);
        fadeIn(cat_top,150);
        fadeIn(cat_middle,300);

        moveToBottom(cardLeft,1f,80,0,1);
        moveToBottom(cardRight,1f,80,150,1);
        moveToTop(cardLeft2,80,200,1);
        moveToTop(cardRight2,80,250,1);

        moveToTop(card4,80,200,1);
        moveToTop(card5,70,250,2);
        moveToTop(card6,60,300, 3);

        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardLeft: {
                showContact();
                break;
            }
            //getActivity().overridePendingTransition(R.anim.goup, R.anim.godown);
            case R.id.cardRight2: {
                Intent wallpaper = new Intent(getActivity(), Wallpaper.class);
                startActivity(wallpaper);
                getActivity().overridePendingTransition(0, 0);
                break;
            }
            case R.id.cardLeft2: {
                Intent intro = new Intent(getActivity(), LottieIntro.class);
                startActivity(intro);
                break;
            }
            case R.id.cardRight: {
                showDonation();
                break;
            }
        }
    }

    private void showContact() {
        final View dialogView = View.inflate(getActivity(), R.layout.dialog_contact, null);
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
                        return new PorterDuffColorFilter(getColor(getContext(), R.color.dialog_contact2), PorterDuff.Mode.SRC_IN);
                    }});
        dialogbg0.addValueCallback(
                new KeyPath("circSTART","**"),
                LottieProperty.COLOR_FILTER,
                new SimpleLottieValueCallback<ColorFilter>() {
                    @Override
                    public ColorFilter getValue(LottieFrameInfo<ColorFilter> frameInfo) {
                        return new PorterDuffColorFilter( getColor(getContext(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);
                    }});

        imageView = dialog.findViewById(R.id.closeDialogImg);
        iconTG = dialog.findViewById(R.id.iconTG);
        iconMAIL = dialog.findViewById(R.id.iconMAIL);
        closeBG = dialog.findViewById(R.id.closeBG);
        tg = dialog.findViewById(R.id.tg);
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

        tg.setAlpha(0.0f);
        tg.setScaleX(0.7f);
        tg.setScaleY(0.7f);
        tg.setTranslationY(200);
        tg.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(400).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        iconTG.setAlpha(0.0f);
        iconTG.setScaleX(0.8f);
        iconTG.setScaleY(0.8f);
        iconTG.setTranslationY(250);
        iconTG.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(650).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
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
        closeBG.setAlpha(0.0f);
        closeBG.setScaleX(0.8f);
        closeBG.setScaleY(0.8f);
        closeBG.setTranslationY(300);
        closeBG.animate().translationY(0).scaleX(1).scaleY(1).alpha(0.65f).setStartDelay(400).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setRotation(120);
        imageView.animate().rotation(0).scaleX(1).scaleY(1).alpha(0.8f).setStartDelay(550).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();

    }

    private void showDonation() {
        final View dialogView = View.inflate(getActivity(), R.layout.dialog_donation, null);
        final Dialog dialog = new Dialog(getActivity(), R.style.ApptThemeDialogDonation);
        dialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        dialog.getWindow().setStatusBarColor(Color.TRANSPARENT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);

        imageView = dialog.findViewById(R.id.closeDialogImg);
        iconTG = dialog.findViewById(R.id.iconTG);
        iconMAIL = dialog.findViewById(R.id.iconMAIL);
        closeBG = dialog.findViewById(R.id.closeBG);
        tg = dialog.findViewById(R.id.tg);
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

        tg.setAlpha(0.0f);
        tg.setScaleX(0.7f);
        tg.setScaleY(0.7f);
        tg.setTranslationY(200);
        tg.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(400).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
        iconTG.setAlpha(0.0f);
        iconTG.setScaleX(0.8f);
        iconTG.setScaleY(0.8f);
        iconTG.setTranslationY(250);
        iconTG.animate().translationY(0).scaleX(1).scaleY(1).alpha(1f).setStartDelay(650).setDuration(650).setInterpolator(new FastOutSlowInInterpolator()).start();
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
        closeBG.setAlpha(0.0f);
        closeBG.setScaleX(0.8f);
        closeBG.setScaleY(0.8f);
        closeBG.setTranslationY(300);
        closeBG.animate().translationY(0).scaleX(1).scaleY(1).alpha(0.0f).setStartDelay(400).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();
        imageView.setAlpha(0.0f);
        imageView.setScaleX(0.5f);
        imageView.setScaleY(0.5f);
        imageView.setRotation(120);
        imageView.animate().rotation(0).scaleX(1).scaleY(1).alpha(0.8f).setStartDelay(550).setDuration(750).setInterpolator(new FastOutSlowInInterpolator()).start();

    }
    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (anchor_cardleft.getX() + (anchor_cardleft.getWidth() / 2));
        int cy = (int) (anchor_cardleft.getY()) + anchor_cardleft.getHeight() - 90;


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
            tg.animate().translationY(200).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            iconTG.animate().translationY(200).scaleX(0.7f).scaleY(0.7f).alpha(0f).setStartDelay(130).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            closeBG.animate().translationY(300).scaleX(0.8f).scaleY(0.8f).alpha(0f).setStartDelay(0).setDuration(500).setInterpolator(new FastOutSlowInInterpolator()).start();
            imageView.animate().rotation(120).scaleX(0.5f).scaleY(0.5f).alpha(0f).setStartDelay(0).setDuration(600).setInterpolator(new FastOutSlowInInterpolator()).start();
            anim.setDuration(750);
            anim.setStartDelay(100);
            anim.start();

        }
    }
}