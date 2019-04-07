package com.moelle.deepdarkness.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.moelle.deepdarkness.GifDrawableImageViewTarget;
import com.moelle.deepdarkness.R;
import com.stephentuso.welcome.WelcomePage;
import com.stephentuso.welcome.WelcomeUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Slide_Backup extends Fragment implements WelcomePage.OnChangeListener {

    private ViewGroup rootLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.slide_01, container, false);
    }

   // @Override
    //public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    //    super.onViewCreated(view, savedInstanceState);
    //
    //   rootLayout = (ViewGroup) view.findViewById(R.id.layout);

    //    ImageView image_view = view.findViewById(R.id.slide_01);

    //    Glide.with(this)
    //            .load("https://i.imgur.com/tVoXBc2.gif")
    //            .into(new GifDrawableImageViewTarget(image_view, 1));

    //}

    @Override
    public void onWelcomeScreenPageScrolled(int pageIndex, float offset, int offsetPixels) {
        if (rootLayout != null)
            WelcomeUtils.applyParallaxEffect(rootLayout, true, offsetPixels, 0.3f, 0.2f);
    }

    @Override
    public void onWelcomeScreenPageSelected(int pageIndex, int selectedPageIndex) {
        //Not used
    }

    @Override
    public void onWelcomeScreenPageScrollStateChanged(int pageIndex, int state) {
        //Not used
    }

}
