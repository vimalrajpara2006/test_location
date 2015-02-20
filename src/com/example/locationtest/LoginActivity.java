package com.example.locationtest;

import java.util.Arrays;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.locationtest.main.MainActivity;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

public class LoginActivity extends Activity {

	Context currentContext;
	private static final String TAG = "LoginActivity";
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			onSessionStateChange(session, state, exception);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);

		currentContext = this;

		LoginButton loginButton = (LoginButton) findViewById(R.id.btn_fb_login);
		loginButton.setReadPermissions(Arrays.asList("public_profile"));
		loginButton
				.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
					@Override
					public void onUserInfoFetched(GraphUser user) {
						if (user != null) {
							Toast.makeText(currentContext,
									"FB success " + user.getName(),
									Toast.LENGTH_SHORT).show();
						}
					}

				});
		// loginButton.setOnErrorListener(new OnErrorListener() {
		//
		// @Override
		// public void onError(FacebookException error) {
		// // TODO Auto-generated method stub
		// Toast.makeText(currentContext, "FB Error", Toast.LENGTH_SHORT)
		// .show();
		// }
		// });
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (state.isOpened()) {
			Log.i(TAG, "Logged in...");
			Intent intentMain = new Intent(currentContext, MainActivity.class);
			startActivity(intentMain);
			finish();
		} else if (state.isClosed()) {
			Log.i(TAG, "Logged out...");
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		uiHelper.onSaveInstanceState(outState);
	}
}
