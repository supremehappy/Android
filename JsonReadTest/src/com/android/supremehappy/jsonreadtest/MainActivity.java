package com.android.supremehappy.jsonreadtest;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button btn; 
	EditText edit_input;
	TextView txt_result;
	
	String DEFAULT_URL="http://192.168.1.149:8081/AndroidServer2/JsonServlet";
	
	StringBuffer sb = new StringBuffer();
	StringBuffer result = new StringBuffer();
	Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit_input=(EditText) findViewById(R.id.edit_input);
		edit_input.setText(DEFAULT_URL);
		
		txt_result=(TextView) findViewById(R.id.txt_result);
		
		btn=(Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				ConnectThread ct = new ConnectThread();
				
				ct.start();
				
			}
			
		});
	}
	
	//----------------------------------------------- 내부 클래스
	class ConnectThread extends Thread{
		public void run(){
			try{
				URL url = new URL(DEFAULT_URL);
				HttpURLConnection conn=(HttpURLConnection)url.openConnection();
				
				String line = "";
				
				InputStream is = conn.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);	
				BufferedReader br = new BufferedReader(isr);
				
				while((line=br.readLine())!=null){
					sb.append(line);
				}
				
				JSONObject jason = new JSONObject(sb.toString());
				
				String key = jason.getString("PRICE");
				String key2 = jason.getString("NAME");
				
				result.append(key +", " +key2+"\n");
			}catch(Exception e){
				
			}
			
			handler.post(new Runnable(){

				@Override
				public void run() {
					txt_result.setText(result.toString());
					
				}
				
			});
		}
	}
}
