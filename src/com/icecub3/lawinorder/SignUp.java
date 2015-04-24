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

public class SignUp extends Activity {

	static final String str_parent_activity = null;
	static final String str_email = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		final UsersHelper hUsers = new UsersHelper(this);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		Typeface Goudy = Typeface.createFromAsset(getAssets(),
				"fonts/Goudy.ttf");
		TextView goudyView = (TextView) findViewById(R.id.AppName);
		goudyView.setTypeface(Goudy);

		Button signup = (Button) findViewById(R.id.bRegister);
		final EditText fullName = (EditText) findViewById(R.id.etFullName);
		final EditText company = (EditText) findViewById(R.id.etCompanyName);
		final EditText phoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		final EditText email = (EditText) findViewById(R.id.etEmail);
		final EditText password = (EditText) findViewById(R.id.etPassword);
		final EditText confirmPassword = (EditText) findViewById(R.id.etConfirmPassword);

		email.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {

					if (!android.util.Patterns.EMAIL_ADDRESS.matcher(
							email.getText()).matches()) {
						Toast errorToast;
						errorToast = Toast.makeText(getApplicationContext(),
								"Invalid email address", Toast.LENGTH_SHORT);
						errorToast.show();
					}

				}
			}
		});

		phoneNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if (!hasFocus) {
					if (!android.util.Patterns.PHONE.matcher(
							phoneNumber.getText()).matches()) {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;
						Toast errorToast;
						errorToast = Toast.makeText(context,
								"Invalid phone number", duration);
						errorToast.show();
					}
				}
			}
		});

		confirmPassword
				.setOnFocusChangeListener(new View.OnFocusChangeListener() {

					@Override
					public void onFocusChange(View v, boolean hasFocus) {
						// TODO Auto-generated method stub
						if (!hasFocus) {
							if (!password
									.getText()
									.toString()
									.equals(confirmPassword.getText()
											.toString())) {
								Context context = getApplicationContext();
								int duration = Toast.LENGTH_SHORT;
								Toast errorToast;
								errorToast = Toast.makeText(context,
										"Passwords do not match", duration);
								errorToast.show();
							}
						}
					}
				});

		signup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Toast t = null;

				if (hUsers.getIdFromEmail(email.getText().toString()) > 0) {
					email.requestFocus();
					password.setText("");
					confirmPassword.setText("");
					t = Toast.makeText(getApplicationContext(),
							"Email already registered", Toast.LENGTH_LONG);
					t.show();
				} else if (hUsers.getIdFromEmail(email.getText().toString()) < 1) {

					if (!password.getText().toString()
							.equals(confirmPassword.getText().toString())) {
						Context context = getApplicationContext();
						int duration = Toast.LENGTH_SHORT;
						Toast errorToast;
						errorToast = Toast.makeText(context,
								"Passwords do not match", duration);
						errorToast.show();
					} else {

						Users user = new Users();
						user.setEmail(email.getText().toString());
						user.setPassword(password.getText().toString());
						user.setName(fullName.getText().toString());
						user.setCompany(company.getText().toString());
						user.setPhone(phoneNumber.getText().toString());
						hUsers.addUser(user);

						Intent showLoginPage = new Intent(
								"com.icecub3.lawinorder.action.Login");
						showLoginPage.putExtra(str_parent_activity, "SignUp");
						showLoginPage.putExtra(str_email, email.getText()
								.toString());
						startActivity(showLoginPage);
						Toast confirmationToast;
						confirmationToast = Toast.makeText(
								getApplicationContext(),
								"Registration completed successfully",
								Toast.LENGTH_SHORT);
						confirmationToast.show();
					}
				}
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
	// getMenuInflater().inflate(R.menu.sign_up, menu);
	// return true;
	// }

}
