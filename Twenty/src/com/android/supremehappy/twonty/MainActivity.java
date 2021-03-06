package com.android.supremehappy.twonty;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	int counter=0, n;
	EditText edit; 
	TextView result;
	Button start, exit;
	boolean check;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Random rnd = new Random();
		
		n=rnd.nextInt(501)+500;
		edit = (EditText) findViewById(R.id.editText1);
		result = (TextView) findViewById(R.id.textView2);
		start=(Button) findViewById(R.id.btn_start);
		exit=(Button) findViewById(R.id.btn_exit);
		
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		

		start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter++;
				
				
				int p = Integer.parseInt(edit.getText().toString());
				
				if(p< 500 || p>1000){
					result.setText("입력값이 500~1000 넘어감");
				}else if(p==n){
					result.setText(counter + "번째에서 정답");
					check = true;
				}else if(p>n){
					result.setText(p+"보다 작은값");
				}else if(p<n){
					result.setText(p+"보다 큰값");
				}
				
				if(counter >5 && !check){
					result.setText("5회초과, game over!"+n);
				}
			}
			
		});
	}
	
}
