package com.moelle.deepdarkness.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.klinker.android.simple_videoview.SimpleVideoView;
import com.moelle.deepdarkness.LottieTutorial;
import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_3 extends Fragment implements View.OnClickListener {

    private SimpleVideoView videoView;
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
        Button tutorial_btn = v.findViewById(R.id.tutorial_btn);
        tutorial_btn.setOnClickListener(this);
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
