package com.android.supremehappy.callingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btn_call;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_call =(Button) findViewById(R.id.btn_call);
		btn_call.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent it = new Intent("bbb.ccc.ddd");
				
				it.putExtra("MY_KEY", "송신");
				startActivityForResult(it, 1);
				
			}
			
		});
	}
}
