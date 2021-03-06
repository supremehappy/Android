package com.android.supremehappy.intenttest1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btn_yut, btn_twe, btn_dice, btn_exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_yut=(Button)findViewById(R.id.btn_yut);
		btn_yut.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,YutActivity.class);
				startActivity(itt);
				
			}
		});
		
		btn_twe=(Button)findViewById(R.id.btn_twe);
		btn_twe.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,TewtyActivity.class);
				startActivity(itt);
				
			}
		});
		
		btn_dice=(Button)findViewById(R.id.btn_dice);
		btn_dice.setOnClickListener(new OnClickListener(){

			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,DiceActivity.class);
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
