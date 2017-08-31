package com.android.supremehappy.rabbitracing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class IntroActivity extends Activity {

	Handler h = new Handler(){
		public void handleMessage(Message msg){
			Intent it = new Intent(IntroActivity.this,MainActivity.class);
			startActivity(it);
			finish();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				
				try{
					Thread.sleep(1000);
					h.sendEmptyMessage(0);
				}catch(Exception e){
					
				}
				
			}
			
		}).start();
	}
}
