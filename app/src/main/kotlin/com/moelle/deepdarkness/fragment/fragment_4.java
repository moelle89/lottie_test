package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
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
import com.moelle.deepdarkness.LottieTutorial;
import com.moelle.deepdarkness.MainActivity;
import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_4 extends Fragment implements View.OnClickListener{

    private TextView title,description;
    public fragment_4() {
        // Required empty public constructor
    }
/*
        Uri DASHBOARD_HEAD = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.dashboardhero);
        videoView = v.findViewById(R.id.dashboard_head);

        videoView.setErrorTracker(new SimpleVideoView.VideoPlaybackErrorTracker() {
            @Override
            public void onPlaybackError(Exception e) {
                e.printStackTrace();
                Snackbar.make(videoView, "Uh oh, error playing!", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        videoView.start(DASHBOARD_HEAD);
 */

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
        View v = inflater.inflate(R.layout.fragment_3, null);

        //ini animations
        Animation anim_icon = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        anim_icon.setStartOffset(200);
        anim_icon.setDuration(600);
        Animation anim_title = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_title.setStartOffset(300);
        Animation anim_text = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_text.setStartOffset(500);
        anim_text.setDuration(600);
        Animation anim_button = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_button.setStartOffset(550);
        anim_button.setDuration(950);
        CardView card = v.findViewById(R.id.cardView);
        card.setScaleX(0.5f);
        card.setScaleY(0.5f);
        card.setAlpha(0f);
        card.animate().scaleX(1).scaleY(1).alpha(1f).setStartDelay(200).setDuration(800).setInterpolator(new FastOutSlowInInterpolator()).start();

        LottieAnimationView iconhowto = v.findViewById(R.id.iconhowto);
        final int gear = ContextCompat.getColor(v.getContext(), R.color.colorAccent_light);
        final int lupe = ContextCompat.getColor(v.getContext(), R.color.textColor);
        final int bg = ContextCompat.getColor(v.getContext(), R.color.background);
        SimpleColorFilter filterGear = new SimpleColorFilter(gear);
        SimpleColorFilter filterLupe = new SimpleColorFilter(lupe);
        SimpleColorFilter filterCheckbox = new SimpleColorFilter(lupe);
        KeyPath keyPathGear = new KeyPath("gear", "**");
        KeyPath keyPathLupe = new KeyPath("lupe", "**");
        KeyPath keyPathcheckbox = new KeyPath("checkmark", "**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filterLupe);
        LottieValueCallback<ColorFilter> callback2 = new LottieValueCallback<ColorFilter>(filterGear);
        LottieValueCallback<ColorFilter> callback3 = new LottieValueCallback<ColorFilter>(filterCheckbox);
        iconhowto.addValueCallback(keyPathLupe, LottieProperty.COLOR_FILTER, callback);
        iconhowto.addValueCallback(keyPathGear, LottieProperty.COLOR_FILTER, callback2);
        iconhowto.addValueCallback(keyPathcheckbox, LottieProperty.COLOR_FILTER, callback3);
        LottieAnimationView intro_img = v.findViewById(R.id.intro_img);
        SimpleColorFilter filter = new SimpleColorFilter(gear);
        SimpleColorFilter filterbg = new SimpleColorFilter(bg);
        KeyPath keyPath = new KeyPath("rect", "**");
        LottieValueCallback<ColorFilter> callback4 = new LottieValueCallback<ColorFilter>(filter);
        intro_img.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback4);

        iconhowto.setAnimation(anim_icon);
        title = v.findViewById(R.id.titlehowto);
        title.setAnimation(anim_title);
        description = v.findViewById(R.id.description);
        description.setAnimation(anim_text);
        Button tutorial_btn = v.findViewById(R.id.tutorial_btn);
        tutorial_btn.setAnimation(anim_button);
        tutorial_btn.setOnClickListener(this);
        LottieAnimationView btn_ripple = v.findViewById(R.id.ripplehowto);
        btn_ripple.animate().alpha(1f).setDuration(800).setStartDelay(2000);
        return  v ;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tutorial_btn:
                Intent tutorial = new Intent(getActivity(), LottieTutorial.class);
                startActivity(tutorial);
                getActivity().overridePendingTransition(R.anim.goup, R.anim.godown);
        }
    }

}