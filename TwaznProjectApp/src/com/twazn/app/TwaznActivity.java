package com.twazn.app;


import android.app.Activity;
import android.os.Bundle;



public class TwaznActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twazn);
        
        ActivityBar.getInstance().connectToActivity(this);
    }
}



