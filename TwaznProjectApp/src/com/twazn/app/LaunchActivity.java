package com.twazn.app;



import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class LaunchActivity extends Activity {
    /** Called when the activity is first created. */
	private static String KEY_EMAIL = "email";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();
    ImageLoader.getInstance().init(config);

        //check if user is logged in
        SharedPreferences prefs = this.getSharedPreferences(
        	      "com.twazn.app", Context.MODE_PRIVATE);
        String email = prefs.getString(KEY_EMAIL, ""); 
        if(email.equalsIgnoreCase(""))
        {
        	Intent login = new Intent(getApplicationContext(), SigninActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(login);
          finish();
        }
        else
        {
        	Intent timeline = new Intent(getApplicationContext(), TimeLineActivity.class);
            timeline.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(timeline);
            finish();
        }
        /**
        ActivityBar ab = ActivityBar.getInstance();
        ab.connectToActivity(this);
        //HACK, for now just start the trail running activity
        ab.pressButtonById(R.id.navProfile, true);	*/		//Manually press the airports button
    }
    

}