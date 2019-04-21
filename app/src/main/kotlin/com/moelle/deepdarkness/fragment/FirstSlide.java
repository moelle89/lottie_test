package com.moelle.deepdarkness.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moelle.deepdarkness.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstSlide extends Fragment {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //LottieAnimationView animationView4 = getView().findViewById(R.id.animation_view4);
        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.intro, container, false);

    }
}