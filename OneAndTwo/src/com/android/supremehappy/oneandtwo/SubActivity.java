package com.android.supremehappy.oneandtwo;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends Activity {

	Button btn_exit;
	TextView txt_result1,txt_result2;
	String one ="짝", two="홀";
	String result="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub_activity);
		
		btn_exit=(Button) findViewById(R.id.btn_back);
		btn_exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
		
		Random rd = new Random();
		int r = rd.nextInt(10);
		
		txt_result1= (TextView) findViewById(R.id.txt_result1);
		txt_result2= (TextView) findViewById(R.id.txt_result2);
		
		if(r%2==0){
			result=one;
			txt_result1.setText(one);
		}else if(r%2!=0){
			result=two;
			txt_result1.setText(two);
		}
		
		String received = getIntent().getStringExtra(MainActivity.FIRST_KEY);
		
		if(received != null){
			if(received.equals(result)){
				txt_result2.setText("맞췄습니다. : "+received);
			}else{
				txt_result2.setText("틀렸습니다. : "+received);
			}
			if(received.equals(result)){
				txt_result2.setText("맞췄습니다. : "+received);
			}else{
				txt_result2.setText("틀렸습니다. : "+received);
			}
		}
		
	}
}

