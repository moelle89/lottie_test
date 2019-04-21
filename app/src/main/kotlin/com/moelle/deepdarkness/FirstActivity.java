package com.moelle.deepdarkness;

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
import com.google.android.material.snackbar.Snackbar;
import com.klinker.android.simple_videoview.SimpleVideoView;

import static com.moelle.deepdarkness.R.id;

public class FirstActivity extends AppCompatActivity {

    //private static final String DASHBOARD_HEAD = "https://bitbucket.org/moelle/media/raw/c6535ca0fa8e14abd83494e12e9067c4a49d29d2/dashboardhero.mp4";

    private SimpleVideoView videoView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        //HttpProxyCacheServer proxy = ((DDApplication) getApplication()).getProxy(this);
        //String DASHBOARD_HEAD = proxy.getProxyUrl("https://bitbucket.org/moelle/media/raw/c6535ca0fa8e14abd83494e12e9067c4a49d29d2/dashboardhero.mp4");

        Uri DASHBOARD_HEAD = Uri.parse("android.resource://" + getPackageName() + "/"
                + R.raw.dashboardhero);
        videoView = findViewById(id.dashboard_head);

        videoView.setErrorTracker(new SimpleVideoView.VideoPlaybackErrorTracker() {
            @Override
            public void onPlaybackError(Exception e) {
                e.printStackTrace();
                Snackbar.make(videoView, "Uh oh, error playing!", Snackbar.LENGTH_INDEFINITE).show();
            }
        });
        videoView.start(DASHBOARD_HEAD);


        ImageView image_view2 = findViewById(id.paypal);
        Glide.with(this).load("https://i.imgur.com/eU8ZRxo.gif").into(image_view2);

        Toolbar toolbar = findViewById(id.toolbar);
        setSupportActionBar(toolbar);
    }

    //DASHBOARD BUTTONS
    public void substratum(View view) { ////button to launch theme on substratum app
        {
            Intent intent = new Intent();
            intent = intent.setClassName("projekt.substratum",
                    "projekt.substratum.MainActivity");
            startActivity(intent);
        }

    }
    public void launchTelegramLink(View view) {   ////button to launch a telegram group
        Intent telegramLink = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/moelle1")); ////Insert your link
        startActivity(telegramLink);
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

                startActivity(new Intent(FirstActivity.this, LottieIntro.class));
                finish();
     }



    public void hide (View v) { ////checkbox to hide icon launcher on drawer
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked()){PackageManager p = getPackageManager();
            ComponentName componentName = new ComponentName(FirstActivity.this, SplashActivity.class);
            p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            finish();

        }
    }

    public void close(View view) { }
}
