package com.icecub3.lawinorder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
//import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		final UsersHelper hUsers = new UsersHelper(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Typeface Goudy = Typeface.createFromAsset(getAssets(),
				"fonts/Goudy.ttf");
		TextView goudyView = (TextView) findViewById(R.id.AppName);
		goudyView.setTypeface(Goudy);

		final Button login = (Button) findViewById(R.id.bLogin);
		final Button signup = (Button) findViewById(R.id.bSignup);

		final EditText email = (EditText) findViewById(R.id.etLoginEmail);
		final EditText password = (EditText) findViewById(R.id.etLoginPassword);

		Intent intent = getIntent();
		if (intent.getStringExtra(SignUp.str_parent_activity) != null) {
			email.setText(intent.getStringExtra(SignUp.str_email));
			password.requestFocus();
		}

		// Validation
		email.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
							email.getText()).matches()) {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;
						Toast errorToast;
						errorToast = Toast.makeText(context,
								"Invalid email address", duration);
						errorToast.show();
					}
				}
			}
		});

		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Toast t = null;
				if (hUsers.getIdFromEmailPass(email.getText().toString(),
						password.getText().toString()) < 1) {
					password.setText("");
					t = Toast.makeText(getApplicationContext(),
							"Invalid username or password", Toast.LENGTH_SHORT);
				} else if (hUsers.getIdFromEmailPass(
						email.getText().toString(), password.getText()
								.toString()) > 0) {
					t = Toast.makeText(getApplicationContext(),
							"Login successful", Toast.LENGTH_SHORT);

					Intent showUpcoming = new Intent(
							"com.icecub3.lawinorder.action.Upcoming");
					startActivity(showUpcoming);
				}

				t.show();
			}
		});

		signup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent showSignUpPage = new Intent(
						"com.icecub3.lawinorder.action.SignUp");
				startActivity(showSignUpPage);
			}
		});

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
	// getMenuInflater().inflate(R.menu.login, menu);
	// return true;
	// }

}
