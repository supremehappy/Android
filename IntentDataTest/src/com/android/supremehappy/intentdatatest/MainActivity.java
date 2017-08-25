package com.android.supremehappy.intentdatatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	Button btn_menu1, btn_menu2, btn_exit;
	EditText edit_parent;
	private static final int FIRST=1;
	private static final int SECOND=2;
	public static final String FIRST_KEY="FIRST";
	public static final String SECOND_KEY="SECOND";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit_parent = (EditText) findViewById(R.id.edit_parent);
		
		btn_menu1 = (Button) findViewById(R.id.btn_menu1);
		btn_menu1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				Intent itt = new Intent(MainActivity.this,FirstChild.class);
				String data = edit_parent.getText().toString();
				
				itt.putExtra(MainActivity.FIRST_KEY, data);
				startActivityForResult(itt,MainActivity.FIRST);
				
			}
			
		});
		
		btn_menu2 = (Button) findViewById(R.id.btn_menu2);
		btn_menu2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				Intent itt = new Intent(MainActivity.this,SecondChild.class);
				String data = edit_parent.getText().toString();
				
				itt.putExtra(MainActivity.SECOND_KEY, data);
				startActivityForResult(itt,MainActivity.SECOND);
				
			}
			
		});
		
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if(requestCode==MainActivity.FIRST){ // first 액티비티에서 수신된 데이터
			if(resultCode==RESULT_OK){
				String result = data.getExtras().getString(MainActivity.FIRST_KEY);
				
				result="fisrt activity : " + result;
				edit_parent.setText(result);
			}else{
				String result="error";
				edit_parent.setText(result);
			}
		}else if(requestCode==MainActivity.SECOND){ // second 액티비티에서 수신된 데이터
			if(resultCode==RESULT_OK){
				String result = data.getExtras().getString(MainActivity.SECOND_KEY);
				
				result="second activity : " + result;
				edit_parent.setText(result);
			}else{
				String result="error";
				edit_parent.setText(result);
			}
		}
		
		
	}
}

