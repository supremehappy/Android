package com.android.supremehappy.tts;

import java.util.Locale;
import java.util.Random;

import com.android.supremehappy.yutgame2.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class YutActivity extends Activity implements TextToSpeech.OnInitListener{

	int[] yut = {R.drawable.yut_1, R.drawable.yut_0};
	String[] mal = {"모","도","개","걸","윷"};
	ImageView iv1, iv2,iv3,iv4 ;
	Button btn_start;
	TextView result;
	int n,n1,n2,n3,n4;
	Random rd = new Random();
	Handler h = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			case 0 : 
				result.setText(mal[n]);
				break;
			case 1 : 
				result.setText(mal[n]);
				break;
			case 2 : 
				result.setText(mal[n]);
				break;
			case 3 : 
				result.setText(mal[n]);
				break;
			case 4 : 
				result.setText(mal[n]);
				break;
			case 5 : 
				drawYut();
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		iv1 = (ImageView) findViewById(R.id.imageView1);
		iv2 = (ImageView) findViewById(R.id.imageView2);
		iv3 = (ImageView) findViewById(R.id.imageView3);
		iv4 = (ImageView) findViewById(R.id.imageView4);
		
		result = (TextView) findViewById(R.id.textView1);
		
		btn_start=(Button)findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				new Thread(new Runnable(){
					
					public void run(){
						for(int i = 0; i<10; i++){
							try{
								Thread.sleep(100);//0.1초 대기
								
								//핸들러 호출
								h.sendEmptyMessage(5);	
								
							}catch(Exception e){
								
							}
							
						}
						switch(n){
						case 0 : 
							h.sendEmptyMessage(0);
							break;
						case 1 : 
							h.sendEmptyMessage(1);
							break;
						case 2 : 
							h.sendEmptyMessage(2);
							break;
						case 3 : 
							h.sendEmptyMessage(3);
							break;
						case 4 : 
							h.sendEmptyMessage(4);
							break;
						}
					}
					
				}).start();
				
				Log.e("yut", String.valueOf(n));
				
			}
			
		});
	}
	
	private void drawYut(){
		n1 = 1- rd.nextInt(10)/6;
		n2 = 1- rd.nextInt(10)/6;
		n3 = 1- rd.nextInt(10)/6;
		n4 = 1- rd.nextInt(10)/6;
		
		iv1.setImageResource(yut[n1]);
		iv2.setImageResource(yut[n2]);
		iv3.setImageResource(yut[n3]);
		iv4.setImageResource(yut[n4]);
		
		n = n1+n2+n3+n4;
		
		
		Log.d("yut", String.valueOf(n));
	}
}