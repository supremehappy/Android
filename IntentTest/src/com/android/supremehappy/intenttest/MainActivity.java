package com.android.supremehappy.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btn_exit, btn_change;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_change=(Button)findViewById(R.id.btn_change);
		btn_change.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,SecondActivity.class);
				startActivity(itt);
				
			}
		});
		
		btn_exit=(Button)findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				finish();
				
			}
		});
	}
}
