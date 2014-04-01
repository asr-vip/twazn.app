package com.twazn.app;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.DTDHandler;

import MySQLLibrary.UserFunctions;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends Activity {

	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputUsername;
	EditText inputEmail;
	EditText inputPassword;
	EditText inputName;
	EditText inputWeight;
	EditText inputHeight;
	Spinner inputSex;
	EditText inputBirthDate;
	EditText inputRePassword;
	Spinner spinnerHeight;
	Spinner spinnerWeight;
	TextView registerErrorMsg;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";

	// Progress Dialog
	private ProgressDialog pDialog;

	private int year;
	private int month;
	private int day;
	static final int DATE_DIALOG_ID = 999;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Set View to register.xml
		setContentView(R.layout.signup);
		// Importing all assets like buttons, text fields
		inputUsername = (EditText) findViewById(R.id.editText1);
		inputName = (EditText) findViewById(R.id.editText2);
		inputEmail = (EditText) findViewById(R.id.editText3);
		inputPassword = (EditText) findViewById(R.id.editText4);
		inputRePassword = (EditText) findViewById(R.id.editText5);
		inputWeight = (EditText) findViewById(R.id.registerWeight);
		inputHeight = (EditText) findViewById(R.id.editText8);
		inputBirthDate = (EditText) findViewById(R.id.editText7);
		inputSex = (Spinner) findViewById(R.id.spinner3);
		spinnerHeight = (Spinner) findViewById(R.id.spinner2);
		spinnerWeight = (Spinner) findViewById(R.id.spinner1);

		registerErrorMsg = (TextView) findViewById(R.id.register_error);

		inputBirthDate.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus)
					showDialog(DATE_DIALOG_ID);
			}
		});
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, 1980, 0, 1);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;

			// set selected date into textview
			inputBirthDate.setText(new StringBuilder().append(year).append("/")
					.append((month + 1)).append("/").append(day));
		}
	};

	public void RegisterUser(View v) {
		new CreateUser().execute();
	}

	private boolean checkPassword(String password, String rePassword) {
		// TODO Auto-generated method stub
		if (password.equals(rePassword))
			return true;

		return false;
	}

	class CreateUser extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(SignupActivity.this);
			pDialog.setMessage("Ì „ «·≈‰‘«¡ ....");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;
			String name = inputName.getText().toString();
			String email = inputEmail.getText().toString();
			String password = inputPassword.getText().toString();
			String rePassword = inputRePassword.getText().toString();
			String userName = inputUsername.getText().toString();
			String height = inputHeight.getText().toString();
			String weight = inputWeight.getText().toString();
			String sex = inputSex.getSelectedItem().toString().substring(0, 1);
			String heightMeasure = spinnerHeight.getSelectedItem().toString();
			String weightMeasure = spinnerWeight.getSelectedItem().toString();
			String birthDate = inputBirthDate.getText().toString();

			float weightF = Float.parseFloat(weight);
			float heightF = Float.parseFloat(height);

			if (weightMeasure.equals("—ÿ·")) {
				weightF /= 2.2;
			}
			if (heightMeasure.equals("«‰‘")) {
				heightF *= 2.54;
			}
			sex = "F";
			if (checkPassword(password, rePassword)) {
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(email, userName,
						password, weightF, heightF, name, sex, birthDate);

				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						//registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS);
						if (Integer.parseInt(res) == 1) {
							Intent dashboard = new Intent(
									getApplicationContext(),
									TimeLineActivity.class);
							startActivity(dashboard);
							// user successfully registred
							// Store user details in shared preference
							SharedPreferences prefs = SignupActivity.this
									.getSharedPreferences("com.twazn.app",
											Context.MODE_PRIVATE);
							prefs.edit().putString(KEY_EMAIL, email).commit();
							// Launch Timeline Screen
						
							// Close all views before launching Timeline
							dashboard.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							
							// Close Registration Screen
							finish();
						} else {
							// Error in registration
							return json.getString(KEY_ERROR_MSG);
							//return "ÕœÀ Œÿ√ „« √À‰«¡ ⁄„·Ì… «· ”ÃÌ·, «·—Ã«¡ ≈⁄«œ… «·„Õ«Ê·…";
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			} else {
				return "ﬂ·„«  «·„—Ê— €Ì— „ ÿ«»ﬁ…. ≈⁄«œ… «·„Õ«Ê·…ø";
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
				registerErrorMsg.setText(file_url);
			}

		}

	}
}
