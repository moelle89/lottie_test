package com.moelle.deepdarkness;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.View;

import com.moelle.deepdarkness.fragment.Slide_02;
import com.moelle.deepdarkness.fragment.Slide_03;
import com.moelle.deepdarkness.fragment.Slide_01;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.WelcomeActivity;
import com.stephentuso.welcome.WelcomeConfiguration;

/**
 * Created by stephentuso on 11/15/15.
 */
public class Welcome extends WelcomeActivity {


    @Override
    protected WelcomeConfiguration configuration() {
        return new WelcomeConfiguration.Builder(this)
                .backButtonNavigatesPages(false)
                .bottomLayout(WelcomeConfiguration.BottomLayout.INDICATOR_ONLY)

                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() { return new Slide_01();
                    }}
                    .background(R.color.background))

                .page(new FragmentWelcomePage() {
                   @Override
                    protected Fragment fragment() { return new Slide_02();
                    }}
                    .background(R.color.background))


                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return new Slide_03();
                    }}
                .background(R.color.background)
                )

                .swipeToDismiss(true)
                .exitAnimation(R.anim.slide_next_in)
                .build();

    }

    public static String welcomeKey() {
        return "WelcomeScreen";}

    public void button(View view) { ////button to launch playstore theme page
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum")); ////Insert your link
        rateLink.setPackage("com.android.vending");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    @Override
    public void completeWelcomeScreen() {
        startActivity(new Intent(this, FirstActivity.class));
        finish();
    }
}