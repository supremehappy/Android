package com.android.supremehappy.intenttest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	
	Button btn_exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_main);
		
		btn_exit=(Button)findViewById(R.id.btn_back);
		btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				finish();
				
			}
		});
	}

}
