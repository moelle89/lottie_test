package com.moelle.deepdarkness.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.moelle.deepdarkness.DirectoryHelper;
import com.moelle.deepdarkness.DownloadService;
import com.moelle.deepdarkness.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_2 extends Fragment implements View.OnClickListener{

    // image url to download
    private static final String accent1 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/1.png";
    private static final String accent2 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/2.png";
    private static final String accent3 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/3.png";
    private static final String accent4 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/4.png";
    private static final String accent5 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/5.png";
    private static final String accent6 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/6.png";
    private static final String accent7 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/7.png";
    private static final String accent8 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/8.png";
    private static final String accent9 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/9.png";
    private static final String accent10 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/10.png";
    private static final String accent11 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/11.png";
    private static final String accent12 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/12.png";
    private static final String accent13 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/13.png";
    private static final String accent14 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/14.png";
    private static final String accent15 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/15.png";
    private static final String accent16 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/16.png";
    private static final String accent17 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/17.png";
    private static final String accent18 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/18.png";
    private static final String accent19 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/19.png";
    private static final String accent20 = "https://raw.githubusercontent.com/moelle89/deepdarkness/master/Accents/20.png";

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_2, container, false);
        CardView card1 = v.findViewById(R.id.accent1);
        CardView card2 = v.findViewById(R.id.accent2);
        CardView card3 = v.findViewById(R.id.accent3);
        CardView card4 = v.findViewById(R.id.accent4);
        CardView card5 = v.findViewById(R.id.accent5);
        CardView card6 = v.findViewById(R.id.accent6);
        CardView card7 = v.findViewById(R.id.accent7);
        CardView card8 = v.findViewById(R.id.accent8);
        CardView card9 = v.findViewById(R.id.accent9);
        CardView card10 = v.findViewById(R.id.accent10);
        CardView card11 = v.findViewById(R.id.accent11);
        CardView card12 = v.findViewById(R.id.accent12);
        CardView card13 = v.findViewById(R.id.accent13);
        CardView card14 = v.findViewById(R.id.accent14);
        CardView card15 = v.findViewById(R.id.accent15);
        CardView card16 = v.findViewById(R.id.accent16);
        CardView card17 = v.findViewById(R.id.accent17);
        CardView card18 = v.findViewById(R.id.accent18);
        CardView card19 = v.findViewById(R.id.accent19);
        CardView card20 = v.findViewById(R.id.accent20);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
        card7.setOnClickListener(this);
        card8.setOnClickListener(this);
        card9.setOnClickListener(this);
        card10.setOnClickListener(this);
        card11.setOnClickListener(this);
        card12.setOnClickListener(this);
        card13.setOnClickListener(this);
        card14.setOnClickListener(this);
        card15.setOnClickListener(this);
        card16.setOnClickListener(this);
        card17.setOnClickListener(this);
        card18.setOnClickListener(this);
        card19.setOnClickListener(this);
        card20.setOnClickListener(this);

        return  v ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accent1: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent1, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent2: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent2, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent3: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent3, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent4: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent4, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent5: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent5, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent6: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent6, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent7: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent7, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent8: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent8, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent9: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent9, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent10: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent10, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent11: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent11, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent12: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent12, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent13: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent13, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent14: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent14, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent15: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent15, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent16: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent16, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent17: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent17, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent18: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent18, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent19: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent19, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
            case R.id.accent20: {
                getActivity().startService(DownloadService.getDownloadService(getContext(), accent20, DirectoryHelper.ROOT_DIRECTORY_NAME.concat("/")));
                Toast();
                break;
            }
        }
    }
    public void Toast(){
        Toast.makeText(getActivity(), "DOWNLOAD SUCCESSFUL", Toast.LENGTH_LONG).show();
    }
}
