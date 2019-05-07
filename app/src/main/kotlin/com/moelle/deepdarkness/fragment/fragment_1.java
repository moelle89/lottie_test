package com.moelle.deepdarkness.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.klinker.android.simple_videoview.SimpleVideoView;
import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragment_1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class fragment_1 extends Fragment {

    private LinearLayout cardRight,cardRight2,cardLeft,cardLeft2,cat_top;
    private FrameLayout cardTop;
    private SimpleVideoView videoView;

    private OnFragmentInteractionListener mListener;

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

        Uri DASHBOARD_HEAD = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+R.raw.dashboardhero);
        videoView = v.findViewById(R.id.dashboard_head);
        videoView.start(DASHBOARD_HEAD);

        // content ini
        cardTop = v.findViewById(R.id.flmiddle);
        cardRight = v.findViewById(R.id.cardRight);
        cardRight2 = v.findViewById(R.id.cardRight2);
        cardLeft = v.findViewById(R.id.cardLeft);
        cardLeft2 = v.findViewById(R.id.cardLeft2);
        cat_top = v.findViewById(R.id.cat_top);

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}