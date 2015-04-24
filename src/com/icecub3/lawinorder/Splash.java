package com.icecub3.lawinorder;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;

//import android.view.Menu;

public class Splash extends Activity {

	String nextPage = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Thread splashTimer = new Thread() {
			public void run() {
				try {
					sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent showNextPage = new Intent(nextPage);
					startActivity(showNextPage);
				}
			}

		};
		splashTimer.start();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.splash, menu);
	// return true;
	// }

}
