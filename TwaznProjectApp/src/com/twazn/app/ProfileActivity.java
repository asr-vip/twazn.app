package com.twazn.app;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;




public class ProfileActivity extends Activity  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        
        ActivityBar.getInstance().connectToActivity(this);
    }
    
    public void logout(View v)
    {
		SharedPreferences prefs = ProfileActivity.this.getSharedPreferences(
				"com.twazn.app", Context.MODE_PRIVATE);
		prefs.edit().remove("email").commit();
    	Intent login = new Intent(getApplicationContext(), SigninActivity.class);
        login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(login);
      finish();


    }
}



