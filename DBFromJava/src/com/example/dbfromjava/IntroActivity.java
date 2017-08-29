package com.example.dbfromjava;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

public class IntroActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView tv = new TextView(this);
		
		setContentView(tv);
		
		AssetManager assetManager = this.getAssets();
		InputStream is = null;
		
		try{
			is = assetManager.open("txt/test.txt");
			String text = loadTextFile(is);
			tv.setText(text);
		}catch(Exception e){
			tv.setText("error");
		}finally{
			if(is!=null){
				try{
					is.close();
				}catch(Exception e){
					
				}
			}
		}
	}
	
	public String loadTextFile(InputStream is) throws IOException{
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len = 0;
		
		while((len=is.read(bytes))>0){
			byteStream.write(bytes,0,len);
		}
		return new String(byteStream.toByteArray(),"UTF-8");
	}

	
}
