package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

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

        CardView background1 = v.findViewById(R.id.background1);
        CardView background2 = v.findViewById(R.id.background2);
        CardView background3 = v.findViewById(R.id.background3);
        CardView background4 = v.findViewById(R.id.background4);

        // ini Animations
        Animation vonOben = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        Animation vonOben2 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        vonOben2.setStartOffset(70);
        Animation vonOben3 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        vonOben3.setStartOffset(170);
        Animation vonOben4 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_top_to_bottom);
        vonOben4.setStartOffset(220);
        Animation vonUnten = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten.setStartOffset(170);
        Animation vonUnten2 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten2.setStartOffset(220);
        Animation vonUnten3 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten3.setStartOffset(250);
        Animation vonUnten4 = AnimationUtils.loadAnimation(getActivity(),R.anim.anime_bottom_to_top);
        vonUnten4.setStartOffset(280);

        card1.setOnClickListener(this);card1.setAnimation(vonOben);
        card2.setOnClickListener(this);card2.setAnimation(vonOben);
        card3.setOnClickListener(this);card3.setAnimation(vonOben);
        card4.setOnClickListener(this);card4.setAnimation(vonOben);
        card5.setOnClickListener(this);card5.setAnimation(vonOben);
        card6.setOnClickListener(this);card6.setAnimation(vonOben);

        card7.setOnClickListener(this);card7.setAnimation(vonOben2);
        card8.setOnClickListener(this);card8.setAnimation(vonOben2);
        card9.setOnClickListener(this);card9.setAnimation(vonOben2);
        card10.setOnClickListener(this);card10.setAnimation(vonOben2);
        card11.setOnClickListener(this);card11.setAnimation(vonOben2);
        card12.setOnClickListener(this);card12.setAnimation(vonOben2);

        card13.setOnClickListener(this);card13.setAnimation(vonOben3);
        card14.setOnClickListener(this);card14.setAnimation(vonOben3);
        card15.setOnClickListener(this);card15.setAnimation(vonOben3);
        card16.setOnClickListener(this);card16.setAnimation(vonOben3);
        card17.setOnClickListener(this);card17.setAnimation(vonOben3);
        card18.setOnClickListener(this);card18.setAnimation(vonOben3);

        card19.setOnClickListener(this);card19.setAnimation(vonOben4);
        card20.setOnClickListener(this);card20.setAnimation(vonOben4);

        background1.setAnimation(vonUnten);
        background2.setAnimation(vonUnten2);
        background3.setAnimation(vonUnten3);
        background4.setAnimation(vonUnten4);

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
