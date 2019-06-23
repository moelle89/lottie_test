package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.moelle.deepdarkness.R;
import static com.moelle.deepdarkness.AnimationPack.fadeIn;
import static com.moelle.deepdarkness.AnimationPack.moveToTop;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_3 extends Fragment implements View.OnClickListener {

    private TextView title, description;

    public fragment_3() {
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
        getActivity().getWindow().setStatusBarColor(ContextCompat.getColor(getContext(),R.color.overlay_head));

        View v = inflater.inflate(R.layout.fragment_3,null);
        //ini animations
        CardView card = v.findViewById(R.id.cardView);
        card.setScaleX(0.5f);
        card.setScaleY(0.5f);
        card.setAlpha(0f);
        card.animate().scaleX(1).scaleY(1).alpha(1f).setStartDelay(200).setDuration(550).setInterpolator(new FastOutSlowInInterpolator()).withLayer().start();

        LottieAnimationView iconhowto = v.findViewById(R.id.iconhowto);
        final int gear = ContextCompat.getColor(v.getContext(), R.color.colorAccent_light);
        final int rect = ContextCompat.getColor(v.getContext(), R.color.colorAccent_dark);
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
        SimpleColorFilter filter = new SimpleColorFilter(rect);
        SimpleColorFilter filterbg = new SimpleColorFilter(bg);
        KeyPath keyPath = new KeyPath("rect", "**");
        LottieValueCallback<ColorFilter> callback4 = new LottieValueCallback<ColorFilter>(filter);
        intro_img.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback4);

        fadeIn(iconhowto,200);
        title = v.findViewById(R.id.titlehowto);
        fadeIn(title,600);
        description = v.findViewById(R.id.description);
        fadeIn(description,850);
        Button tutorial_btn = v.findViewById(R.id.tutorial_btn);
        moveToTop(tutorial_btn,150,400,2);
        tutorial_btn.setOnClickListener(this);
        LottieAnimationView btn_ripple = v.findViewById(R.id.ripplehowto);
        fadeIn(btn_ripple,1800);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tutorial_btn:
                Intent tutorial = new Intent(getActivity(), LottieTutorial.class);
                startActivity(tutorial);
                getActivity().overridePendingTransition(R.anim.dialog_enter, R.anim.dialog_exit);
                //getActivity().overridePendingTransition(R.anim.goup, R.anim.godown);
        }
    }
}
