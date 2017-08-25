package com.android.supremehappy.intentdatatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class FirstChild extends Activity {
	
	Button btn_first;
	EditText edit_first;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		
		edit_first = (EditText) findViewById(R.id.edit_first);
		String received = getIntent().getStringExtra(MainActivity.FIRST_KEY);
		
		if(received != null){
			edit_first.setText("수신된 데이터 : "+received);
		}
		
		btn_first = (Button) findViewById(R.id.btn_first);
		btn_first.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Intent itt = new Intent();
				
				itt.putExtra(MainActivity.FIRST_KEY, edit_first.getText().toString());
				setResult(RESULT_OK,itt);
				finish();
				
			}
			
		});
	}
}
