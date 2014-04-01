//
// ActivityBar.java
// Singleton activity bar class
//
// To use with an Activity:
//    * Include a-activity-bar layout file in the layout for your activity's view
//    * Call connectToActivity from your activity's onCreate, after setting the content view
//    
// When a button is pressed, the object will handle the activity switch, etc...


package com.twazn.app;


import java.util.ArrayList;
import java.util.List;
import com.twazn.app.R.color;

import android.app.Activity;
import android.content.Intent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;



public class ActivityBar {

	protected List<ImageButton> m_arrButton;
	protected int m_iCurrentButtonId= -1;
	protected Activity m_currentActivity = null;
	

	
	
	//Connect to the activity that's currently running, call this after setting 
	//the contentview in onCreate
	public void connectToActivity(Activity activity)
	{
		m_currentActivity = activity;
		
		m_arrButton.clear();
		setupButton(R.id.navProfile);
		setupButton(R.id.navNotification);
		setupButton(R.id.navTwazn);
		setupButton(R.id.navTimeline);
		
		
		//If there's a current button set, light it up
		if (m_iCurrentButtonId != -1) 
			pressButtonById(m_iCurrentButtonId);
	}
	
	//Helper function for connectToActivity
	protected void setupButton(int id)
	{
		ImageButton but = (ImageButton)m_currentActivity.findViewById(id);
		m_arrButton.add(but);
		but.setOnTouchListener(new OnTouchListener() {		
			@Override
			public boolean onTouch(View v, MotionEvent me) {
				if (me.getAction() == MotionEvent.ACTION_UP) 
					pressButtonById(v.getId(), true);
				
				return true;
			}
		});				
	}
	
	
	
	//Default to not switching the activity if not specified
	public void pressButtonById(int nIdPressed)
	{
		pressButtonById(nIdPressed, false);
	}
	
	public void pressButtonById(int nIdPressed, boolean bSwitchActivity)
	{
		boolean bFoundButton = false;
		
		//Unpress all the buttons except the one we passed in
		for (ImageButton bt : m_arrButton) {
			if (nIdPressed == bt.getId()) {
				bFoundButton=true;
				bt.setBackgroundResource(color.black);
			}
			else {
				bt.setPressed(false);			
				bt.setBackgroundResource(color.MediumSeaGreen);
			}
		}
	
		
		//If we asked to switch activities and the button passed in was good, do it
		if (bSwitchActivity && bFoundButton && nIdPressed!=m_iCurrentButtonId && m_currentActivity!=null) {  
			switch (nIdPressed) {
			case R.id.navProfile: 	switchActivity(new Intent(m_currentActivity, ProfileActivity.class)); break;
			case R.id.navNotification: 	switchActivity(new Intent(m_currentActivity, NotificationActivity.class)); break;
			case R.id.navTwazn: 		switchActivity(new Intent(m_currentActivity, TwaznActivity.class)); break;
			case R.id.navTimeline: 		switchActivity(new Intent(m_currentActivity, TimeLineActivity.class)); break;
			}
		}		

		m_iCurrentButtonId = bFoundButton ? nIdPressed : -1;		
	}
	

	
	protected void switchActivity(Intent i) {
		m_currentActivity.startActivity(i);
		m_currentActivity.overridePendingTransition(0,0);		//No animation
		m_currentActivity.finish();								//We want to replace the current activity, not stack them
	}


	
	
	
	
	

	//////////////////////////////////////////////////////////////////////////////////////////	
	//Singleton stuff
	//////////////////////////////////////////////////////////////////////////////////////////	
	private static ActivityBar m_hInstance = null;

	private ActivityBar() {
		m_arrButton = new ArrayList<ImageButton>();
	}


	public static synchronized ActivityBar getInstance() {
		if (m_hInstance == null)						//Create on first demand
			m_hInstance = new ActivityBar();
		return m_hInstance;
	}


	//Call once for clean-up ... not sure I even need this, Tom Barry thinks this will all get GC'd 
	//when the program exits.
	public void Destroy() {
		if (m_hInstance != null) {
			//Do other clean up here...

			m_hInstance = null;								//Free up for garbage collection...
		}
	}
	//////////////////////////////////////////////////////////////////////////////////////////	
	//Singleton stuff
	//////////////////////////////////////////////////////////////////////////////////////////	
}
