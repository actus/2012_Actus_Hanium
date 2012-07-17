package com.corea.londonolympic;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class Splash extends Activity{
	private int finishTime = 1000;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Handler handler =    new Handler()
        {
            @Override
			public void handleMessage(Message msg) {
            	finish();		// Activity Á¾·á
				super.handleMessage(msg);
			}
        };

        handler.sendEmptyMessageDelayed(0, finishTime);
	}
}
