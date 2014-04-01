package MySQLLibrary;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserFunctions {

	private JSONParser jsonParser;

	// Testing in localhost using wamp or xampp
	// use http://10.0.2.2/ to connect to your localhost ie http://localhost/
	private static String loginURL = "http://10.0.2.2/ah_login_api/";
	private static String registerURL = "http://10.0.2.2/ah_login_api/";
	private static String login_tag = "login";
	private static String register_tag = "register";

	// constructor
	public UserFunctions() {
		jsonParser = new JSONParser();
	}

	/**
	 * function make Login Request
	 * 
	 * @param email
	 * @param password
	 * */
	public JSONObject loginUser(String email, String password) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.makeHttpRequest(loginURL,"POST", params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}

	/**
	 * function make Login Request
	 * 
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String email, String username, String password, float weight, float height, String name, String sex, String birthDate) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("weight", Float.toString(weight)));
		params.add(new BasicNameValuePair("height", Float.toString(height)));
		params.add(new BasicNameValuePair("sex", sex));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("birthDate", birthDate));

		// getting JSON Object
		JSONObject json = jsonParser.makeHttpRequest(registerURL,"POST", params);
		// return json
		return json;
	}
}
