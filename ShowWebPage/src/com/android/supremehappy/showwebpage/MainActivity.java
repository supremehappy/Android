package com.android.supremehappy.showwebpage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn;
	TextView txt_msg;
	EditText edit_input;
	public static String DEFAULT_URL="https://m.naver.com";
	Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit_input = (EditText) findViewById(R.id.edit_input);
		edit_input.setText(DEFAULT_URL);
		
		txt_msg = (TextView) findViewById(R.id.txt_msg);
		
		btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				String str = edit_input.getText().toString();
				
				ConnectThread ct = new ConnectThread(str);
				
				ct.start();
			}
			
		});
	}
	
	private String requestWeb(String str){
		
		StringBuffer buffer = new StringBuffer();
		
		try{
			URL url = new URL(str); // 입력된 url 로 객체 생성
			HttpURLConnection conn = (HttpURLConnection)url.openConnection(); // http 프로토콜을 이용해서 접속하는 객체 생성
			
			if(conn !=null){
				conn.setConnectTimeout(10000); // 최대 접속 대기 시간
				
				int resCode = conn.getResponseCode(); // 응답코드
				
				if(resCode == HttpURLConnection.HTTP_OK){
					//접속된 객체 -> InputStream 얻고 -> InputStream으로 -> InputStreamReader 생성 -> InputStreamReader 으로 -> BufferedReader 생성
					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					
					String line = null;
					
					while(true){
						line = reader.readLine();
						
						if(line == null){
							break;
						}
						buffer.append(line+"\n");
					}
					reader.close();
					conn.disconnect();
				}
			}
		}catch(Exception e){
			Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
		}
		
		return buffer.toString();
	}
	
	//------------------------------------ 내부 클래스
	class ConnectThread extends Thread{
		String urlStr;
		
		ConnectThread(String urlStr){
			this.urlStr = urlStr;
		}
		
		@Override
		public void run(){
			try{
				final String output = requestWeb(urlStr);
				
				handler.post(new Runnable(){
					public void run(){
						txt_msg.setText(output);
					}
				});
			}catch(Exception e){
				
			}
		}
	}
}