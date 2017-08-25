package com.android.supremehappy.intentdatatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SecondChild extends Activity {

	Button btn_second;
	EditText edit_second;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		
		edit_second = (EditText) findViewById(R.id.edit_second);
		String received = getIntent().getStringExtra(MainActivity.SECOND_KEY);
		
		if(received != null){
			edit_second.setText("수신된 데이터 : "+received);
		}
		
		btn_second = (Button) findViewById(R.id.btn_second);
		btn_second.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent itt = new Intent();
				
				itt.putExtra(MainActivity.SECOND_KEY, edit_second.getText().toString());
				setResult(RESULT_OK,itt);
				finish();
				
			}
			
		});
	}
}
