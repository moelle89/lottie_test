package com.moelle.deepdarkness;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.view.View;

import com.moelle.deepdarkness.fragment.ExampleFragment;
import com.stephentuso.welcome.BasicPage;
import com.stephentuso.welcome.FragmentWelcomePage;
import com.stephentuso.welcome.ParallaxPage;
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

                .page(new BasicPage(R.drawable.fin2,
                        "Welcome to DEEP DARKNESS",
                        "Lets get some dark UI on your device")
                        .background(R.color.background)
                )

                .page(new ParallaxPage(R.layout.parallax_root,
                        "Does it only work on rooted devices?",
                        "No! You only need Substratum and its Samsung-addon.")
                        .background(R.color.background)
                )
                .page(new ParallaxPage(R.layout.parallax_samsung,
                        "Only for Samsung devices",
                        "This theme is supporting the S8, S8+, Note8 and S7 (partial).")
                        .background(R.color.background)
                )

                .page(new FragmentWelcomePage() {
                    @Override
                    protected Fragment fragment() {
                        return new ExampleFragment();
                    }

                }.background(R.color.background))


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