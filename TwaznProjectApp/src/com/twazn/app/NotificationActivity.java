package com.twazn.app;


import android.app.Activity;
import android.os.Bundle;


public class NotificationActivity extends Activity 
{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications);
        
        ActivityBar.getInstance().connectToActivity(this);      
    }
}



