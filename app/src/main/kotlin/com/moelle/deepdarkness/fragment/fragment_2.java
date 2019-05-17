package com.moelle.deepdarkness.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moelle.deepdarkness.DownloadFromURL;
import com.moelle.deepdarkness.R;

import static android.widget.Toast.LENGTH_SHORT;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment {

    private LinearLayout card1;
    private Button btn_test;

    // image url to download
    private static String url = "https://3.bp.blogspot.com/-EFwVj5ztKtQ/V8Qs6Viyl6I/AAAAAAAADWs/031SPYFrUnM-wreztTT4fgPe1yQj3LFhgCPcB/s1600/developer.jpg";


    public fragment_2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        card1 = v.findViewById(R.id.accent1);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting new Async Task

                new DownloadFromURL().execute(url);
                toast();
            }
        });

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
        return  v ;
    }
    protected void toast() {
        Toast.makeText(getActivity(), "DOWNLOAD SUCCESSFUL", LENGTH_SHORT).show();
    }
}
