package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.balysv.materialripple.MaterialRippleLayout;
import com.klinker.android.simple_videoview.SimpleVideoView;
import com.moelle.deepdarkness.LottieTutorial;
import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_1 extends Fragment implements View.OnClickListener {

    private LinearLayout cat_top;
    private CardView cardRight,cardLeft,cardRight2,cardLeft2;
    private FrameLayout cardTop;


    public fragment_1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_1, container, false);

        /*Uri DASHBOARD_HEAD = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.dashboardhero);
        videoView = v.findViewById(R.id.dashboard_head);
        videoView.start(DASHBOARD_HEAD);*/

        // content ini
        cardTop = v.findViewById(R.id.flmiddle);
        cardRight = v.findViewById(R.id.cardRight);
        cardRight2 = v.findViewById(R.id.cardRight2);
        cardLeft = v.findViewById(R.id.cardLeft);
        cardLeft2 = v.findViewById(R.id.cardLeft2);
        cat_top = v.findViewById(R.id.cat_top);
        Button tutorial_btn = v.findViewById(R.id.tutorial_btn);
        cardLeft.setOnClickListener(this);

        // ini Animations
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity(),R.anim.fade_in);
        Animation vonOben = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        Animation vonOben2 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        vonOben2.setStartOffset(70);
        Animation vonUnten = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten.setStartOffset(170);
        Animation vonUnten2 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten2.setStartOffset(220);

        // setup Animation :
        cat_top.animate().alpha(1f).setDuration(2000).setStartDelay(800);
        cardTop.setAnimation(fadeIn);
        cardLeft.setAnimation(vonOben);
        cardRight.setAnimation(vonOben2);
        cardLeft2.setAnimation(vonUnten);
        cardRight2.setAnimation(vonUnten2);


        // Inflate the layout for this fragment
        return  v ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cardLeft:
                Intent tutorial = new Intent(getActivity(), LottieTutorial.class);
                startActivity(tutorial);
                getActivity().overridePendingTransition(R.anim.goup, R.anim.godown);
        }
    }
}
