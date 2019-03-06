package com.moelle.deepdarkness;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import projekt.substrate.SubstratumLoader;

import static com.moelle.deepdarkness.R.id;

public class FirstActivity extends AppCompatActivity {
    FloatingActionButton fab;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        //ImageView image_view = findViewById(R.id.gplus);
        //Glide.with(this).load("https://i.imgur.com/Js6xhDy.gif").into(image_view);

        ImageView image_view2 = findViewById(id.paypal);
        Glide.with(this).load("https://i.imgur.com/zuWN5Ct.gif").into(image_view2);

        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
    }

    //DASHBOARD BUTTONS
    public void substratum(View view) { ////button to launch theme on substratum app
        {
            Intent launchIntent = SubstratumLoader.launchThemeActivity(getApplicationContext(),
                    getIntent(), getString(R.string.ThemeName), getPackageName());
            startActivity(launchIntent);//null pointer check in case package name was not found
        }
    }
    public void launchRateLink(View view) { ////button to launch playstore theme page
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.moelle.blacksammy")); ////Insert your link
        rateLink.setPackage("com.android.vending");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.moelle.blacksammy")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    public void launchtutorialLink(View view) { ////button to launch tutorial link remove this if you not have a tutorial
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum&hl=it")); ////Insert your link
        rateLink.setPackage("com.google.android.apps.docs");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=projekt.substratum&hl=it"));////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    public void launchDriveLink(View view) { ////button to launch a google drive stored extra resources
        Intent rateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/moelle89/DEEPDARKNESS_sub/raw/master/com.moelle.ddsamsung.apk")); ////Insert your link
        rateLink.setPackage("com.google.android.apps.docs");
        Intent webRateLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/moelle89/DEEPDARKNESS_sub/raw/master/com.moelle.ddsamsung.apk")); ////Insert your link
        try {
            startActivity(rateLink);
        } catch (ActivityNotFoundException ex) {
            startActivity(webRateLink);
        }
    }
    public void launchTelegramLink(View view) {   ////button to launch a telegram group
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/moelle1")); ////Insert your link
        startActivity(telegramLink);
    }

    public void launchCircleMe(View view) { ////button to launch google + profile
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/104011287090851876009")); ////Insert your link
        appIntent.setPackage("com.google.android.apps.plus");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/104011287090851876009")); ////Insert your link
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }

    }

    public void launchPaypal(View view) { ////button to launch paypal.me donationslink
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.me/moelle89")); ////Insert your link
        startActivity(telegramLink);
    }


    public void launchEmail(View view) { ////button to send mail to theme dev
        Intent email = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:manuel.moellmann@gmail.com?subject=[Black Panel // Samsung Mod]")); ////Insert email and subject
        startActivity(email);
    }

    public void ReplayIntro(View view) { ////button to repeat intro activity
        findViewById(id.btn_play_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefManager prefManager = new PrefManager(getApplicationContext());
                prefManager.setFirstTimeLaunch(true);

                startActivity(new Intent(FirstActivity.this, Welcome.class));
                finish();
            }
        });
    }

    public void hide (View v) { ////checkbox to hide icon launcher on drawer
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){PackageManager p = getPackageManager();
            ComponentName componentName = new ComponentName(FirstActivity.this, SplashActivity.class);
            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            finish();

        }
    }

    public void close(View view) {
    }
}
