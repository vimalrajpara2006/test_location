package com.example.locationtest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.AppEventsLogger;

public class SplashActivity extends Activity {

	Context currentContext;
	Handler handler;
	Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		currentContext = this;
	}

	@Override
	protected void onStop() {
		if (handler != null && runnable != null) {
			handler.removeCallbacks(runnable);
		}
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (handler != null && runnable != null) {
			handler.removeCallbacks(runnable);
		}
		handlerData();
		AppEventsLogger.activateApp(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		AppEventsLogger.deactivateApp(this);
	}

	private void handlerData() {

		handler = new Handler();
		runnable = new Runnable() {

			@Override
			public void run() {
				Intent intentLogin = new Intent(currentContext,
						LoginActivity.class);
				startActivity(intentLogin);
				finish();
			}
		};
		handler.postDelayed(runnable, 5000);
	}
}
