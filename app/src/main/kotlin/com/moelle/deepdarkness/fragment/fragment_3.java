package com.moelle.deepdarkness.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.klinker.android.simple_videoview.SimpleVideoView;
import com.moelle.deepdarkness.LottieTutorial;
import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_3 extends Fragment implements View.OnClickListener {

    private SimpleVideoView videoView;
    private TextView title,description;
    private ImageView iconhowto;
    public fragment_3() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_3, container, false);

        //ini animations
        Animation anim_card = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        Animation anim_icon = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_icon.setStartOffset(100);
        Animation anim_title = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_title.setStartOffset(300);
        Animation anim_text = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_text.setStartOffset(500);
        Animation anim_button = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        anim_button.setStartOffset(600);

        CardView card = v.findViewById(R.id.cardView);
        card.setAnimation(anim_card);
        iconhowto = v.findViewById(R.id.iconhowto);
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
                break;
        }
    }

}
