package com.moelle.deepdarkness;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.widget.VideoView;

public class SplashActivity extends Activity {

	VideoView videoView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
			videoView = new VideoView(this);
			setContentView(videoView);
			Uri video = Uri.parse("https://raw.githubusercontent.com/moelle89/deepdarkness/master/splash_1.mp4");
			videoView.setVideoURI(video);

			videoView.setOnCompletionListener(new OnCompletionListener() {

				public void onCompletion(MediaPlayer mp) {
                    videoView.start();   //it will start again
				    //jump();
				}

			});
			videoView.start();
		} catch(Exception ex) {
			jump();
		}
	}
	//  Uncomment this function if you want the user to be able to skip this screen
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		try {
			videoView.stopPlayback();
		} catch(Exception ex) {}
		jump();
		return true;
	}

	private void jump() {if(isFinishing())
		return;
		//startActivity(new Intent(this, Welcome.class));
		startActivity(new Intent(this, FirstActivity.class));
		//overridePendingTransition(0, 0);
		finish();
	}
}