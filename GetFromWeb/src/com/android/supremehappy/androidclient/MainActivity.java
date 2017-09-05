package com.android.supremehappy.androidclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button btn;
	EditText edit_input;
	TextView txt_msg;
	XmlPullParserFactory f;
	XmlPullParser xp;
	
	public static String DEFAULT_URL="http://192.168.1.149:8081/AndroidServer/AndroidServerServlet";
	
	Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		edit_input = (EditText)findViewById(R.id.edit_input);
		edit_input.setText(DEFAULT_URL);
		
		txt_msg = (TextView)findViewById(R.id.txt_msg);
		
		btn = (Button)findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				String str = edit_input.getText().toString();
				
				ConnectThread ct = new ConnectThread(str);
				Log.w("testRun","oncilck");
				
				ct.start();
			}
		});
	}
	
	private String request(String urlStr){
		StringBuffer buffer = new StringBuffer();
		Log.w("testRun","request");
		try{
			
			URL url = new URL(urlStr);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			
			if(conn != null) conn.setConnectTimeout(10000);
			
			int resCode = conn.getResponseCode();
				
			if(resCode == HttpURLConnection.HTTP_OK){
				BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					
					String line = null;
					
					while(true){
						line = reader.readLine();
						
						if(line == null) break;
						buffer.append(line+"\n");
					}
					reader.close();
					conn.disconnect();
				}
		}catch(Exception e){
			Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
		}
		
		return buffer.toString();
	}
	
	private void onShow(String str){
		StringBuilder builder = new StringBuilder();
		
		try{
			Log.w("testRun","onshow");
			f= XmlPullParserFactory.newInstance();
			xp = f.newPullParser();
			xp.setInput(new StringReader(str));
			
			int type=0;
			while((type=xp.next())!=XmlPullParser.END_DOCUMENT){
				String  name = xp.getName();
				String position = null;
				String brand= null;
				
				if(name != null && name.equals("CAR")&&type==XmlPullParser.START_TAG){
					int size = xp.getAttributeCount();
					
					for(int i =0; i<size ; i++){
						String attName = xp.getAttributeName(i);
						String attValue = xp.getAttributeValue(i);
						
						if(attName != null && attName.equals("positon")){
							position = attValue;
						}else if(attName != null && attName.equals("brand")){
							brand = attValue;
						}
					}
					if(position != null && brand !=null) builder.append(position+", "+brand+"\n");
				}
				txt_msg.setText(builder.toString());
			}
		}catch(Exception e){
			Toast.makeText(this, "xml error", Toast.LENGTH_SHORT).show();
		}
	}
	
	//---------------------------------------- 내부클래스
	class ConnectThread extends Thread{
		String urlStr;
		
		ConnectThread(String urlStr){
			this.urlStr = urlStr;
		}
		
		@Override
		public void run(){
			try{
				Log.w("testRun","thread");
				final String output = request(urlStr);
				
				handler.post(new Runnable(){
					public void run(){
						onShow(output);
					}
				});
			}catch(Exception e){
				
			}
		}
	}
}
