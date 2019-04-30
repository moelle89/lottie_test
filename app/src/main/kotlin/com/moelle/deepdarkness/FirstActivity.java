package com.moelle.deepdarkness;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.iammert.library.readablebottombar.ReadableBottomBar;
import com.klinker.android.simple_videoview.SimpleVideoView;

import static com.moelle.deepdarkness.R.id;

public class FirstActivity extends AppCompatActivity {

    //private static final String DASHBOARD_HEAD = "https://bitbucket.org/moelle/media/raw/c6535ca0fa8e14abd83494e12e9067c4a49d29d2/dashboardhero.mp4";
    private SimpleVideoView videoView;

    ImageView fab;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);
        ReadableBottomBar nav = findViewById(id.nav);
        nav.setOnItemSelectListener(new ReadableBottomBar.ItemSelectListener() {
            @Override
            public void onItemSelected(int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(FirstActivity.this, "home", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        ReplayIntro();
                        break;
                    case 2:
                        showDiag();
                        break;
                }
            }
        });
        //HttpProxyCacheServer proxy = ((DDApplication) getApplication()).getProxy(this);
        //String DASHBOARD_HEAD = proxy.getProxyUrl("https://bitbucket.org/moelle/media/raw/c6535ca0fa8e14abd83494e12e9067c4a49d29d2/dashboardhero.mp4");
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDiag();

            }
        });

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

    public void ReplayIntro() { ////button to repeat intro activity
                startActivity(new Intent(FirstActivity.this, LottieIntro.class));
                finish();
     }

    private void launchTutorial() {
        startActivity(new Intent(this, LottieTutorial.class));
        overridePendingTransition(0, 0);
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
    //
    //
    private void showDiag() {

        final View dialogView = View.inflate(this,R.layout.dialog,null);

        final Dialog dialog = new Dialog(this,R.style.DialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);


        ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                revealShow(dialogView, false, dialog);
            }
        });

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(dialogView, true, null);
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK){

                    revealShow(dialogView, false, dialog);
                    return true;
                }

                return false;
            }
        });



        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.show();
        LottieAnimationView animation_viewNull = dialog.findViewById(R.id.animation_viewNull);
        animation_viewNull.playAnimation();
        animation_viewNull.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animation) {
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
                launchTutorial();
            }
        });

    }

    private void revealShow(View dialogView, boolean b, final Dialog dialog) {

        final View view = dialogView.findViewById(R.id.dialog);

        int w = view.getWidth();
        int h = view.getHeight();

        int endRadius = (int) Math.hypot(w, h);

        int cx = (int) (fab.getX() + (fab.getWidth()/2));
        int cy = (int) (fab.getY())+ fab.getHeight() + 56;


        if(b){
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx,cy, 0, endRadius);

            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(800);
            revealAnimator.start();


        } else {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);

                }
            });
            anim.setDuration(800);
            anim.start();

        }

    }
}
