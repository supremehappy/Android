package com.android.supremehappy.tts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btn_yut, btn_dice, btn_tts,btn_edit2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btn_yut = (Button) findViewById(R.id.btn_yut);
		btn_yut.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Intent itt = new Intent(MainActivity.this,YutActivity.class);
				startActivity(itt);
				
			}
			
		});
		
		btn_dice = (Button) findViewById(R.id.btn_dice);
		btn_dice.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Intent itt = new Intent(MainActivity.this,DiceActivity.class);
				startActivity(itt);
				
			}
			
		});
		
		btn_tts = (Button) findViewById(R.id.btn_tts);
		btn_tts.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,TTSActivity.class);
				startActivity(itt);
				
			}
			
		});
		
		btn_edit2 = (Button) findViewById(R.id.btn_edit2);
		btn_edit2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,EditActivity.class);
				startActivity(itt);
				
			}
			
		});
	}
}