package com.android.supremehappy.oneandtwo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final int FIRST=1;
	public static final String FIRST_KEY="FIRST";
	Button btn_send, btn_exit;
	EditText edit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit = (EditText)findViewById(R.id.edit);
		
		btn_send=(Button)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,SubActivity.class);
				String data = edit.getText().toString();
				
				itt.putExtra(MainActivity.FIRST_KEY, data);
				startActivityForResult(itt,MainActivity.FIRST);
			}
			
		});
		
		btn_exit=(Button)findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}
}
