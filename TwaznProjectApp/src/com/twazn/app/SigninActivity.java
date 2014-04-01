package com.twazn.app;

import org.json.JSONException;
import org.json.JSONObject;

import MySQLLibrary.UserFunctions;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends Activity {

	EditText inputEmail;
	EditText inputPassword;
	TextView loginErrorMsg;

    private ProgressDialog pDialog;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_USERNAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setting default screen to signin.xml
		setContentView(R.layout.signin);
		// Importing all assets like buttons, text fields
		inputEmail = (EditText) findViewById(R.id.etEmail);
		inputPassword = (EditText) findViewById(R.id.etPassword);
		loginErrorMsg = (TextView) findViewById(R.id.login_error);

	}

	public void MoveToSignup(View v) {
		Intent intent = new Intent(getApplicationContext(),
				SignupActivity.class);
		startActivity(intent);
	}

	public void MoveToForgetPassword(View v) {
		// TODO Auto-generated method stub
		Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
		startActivity(i);
	}

	public void TrySignin(View v) {
		new signinUser().execute();
	}
	class signinUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SigninActivity.this);
			pDialog.setMessage("Ì „  ”ÃÌ· «·œŒÊ· ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			String email = inputEmail.getText().toString();
			String password = inputPassword.getText().toString();
			
			UserFunctions userFunction = new UserFunctions();
			JSONObject json = userFunction.loginUser(email, password);
			// check for login response
			try {
				if (json.getString(KEY_SUCCESS) != null) {
					//loginErrorMsg.setText("");
					String res = json.getString(KEY_SUCCESS);
					if (Integer.parseInt(res) == 1) {
						// user successfully logged in
						// Store user details in shared prefrence
						SharedPreferences prefs = SigninActivity.this.getSharedPreferences(
								"com.twazn.app", Context.MODE_PRIVATE);
						prefs.edit().putString(KEY_EMAIL, email).commit();
						// Launch Timeline Screen
						Intent timeline = new Intent(getApplicationContext(),
								TimeLineActivity.class);

						// Close all views before launching Timeline
						timeline.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(timeline);

						// Close Login Screen
						finish();
					} else {
						// Error in login
						return "«·»—Ìœ «·≈·ﬂ —Ê‰Ì √Ê ﬂ·„… «·„—Ê— Œ«ÿ∆…";
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		return null;	
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				loginErrorMsg.setText(file_url);
			}

		}

	}

}