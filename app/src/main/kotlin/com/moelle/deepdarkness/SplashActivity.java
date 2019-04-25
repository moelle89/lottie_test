package com.moelle.deepdarkness;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.VideoView;

public class SplashActivity extends Activity {

	VideoView videoView;
	private PrefManager prefManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Checking for first time launch - before calling setContentView()
		prefManager = new PrefManager(this);
		if (!prefManager.isFirstTimeLaunch()) {
			launchDashboard();
			finish();
		}

		try{
			videoView = new VideoView(this);

			setContentView(videoView);
			//Uri video = Uri.parse("https://bitbucket.org/moelle/media/raw/c6535ca0fa8e14abd83494e12e9067c4a49d29d2/splash.mp4");
			Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
					+ R.raw.splash);
			videoView.setVideoURI(video);

			videoView.setOnCompletionListener(new OnCompletionListener() {

				public void onCompletion(MediaPlayer mp) {
					//it will start again - videoView.start();
					jump();
				}

			});
			videoView.start();
		} catch(Exception ex) {
			jump();
		}
	}
	//  Uncomment this function if you want the user to be able to skip this screen
	//@Override
	//public boolean onTouchEvent(MotionEvent event) {
	//	try {
	//		videoView.stopPlayback();
	//	} catch(Exception ex) {}
	//	jump();
	//	return true;
	//}

	private void jump() {if(isFinishing())
		return;
		prefManager.setFirstTimeLaunch(false);
		launchIntroScreen();
	}
	private void launchDashboard() {
		prefManager.setFirstTimeLaunch(false);
		startActivity(new Intent(this, FirstActivity.class));
		finish();
	}
	private void launchIntroScreen() {
		startActivity(new Intent(this, LottieIntro.class));
		finish();
	}
}