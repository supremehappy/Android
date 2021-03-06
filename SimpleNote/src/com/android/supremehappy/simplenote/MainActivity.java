package com.android.supremehappy.simplenote;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private static final String FILENAME = "memo.txt";
	private EditText content;
	private CheckBox check;
	private Button save, load, exit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		check=(CheckBox)findViewById(R.id.check_sd);
		content=(EditText)findViewById(R.id.editText1);
		save=(Button)findViewById(R.id.btn_save);
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(check.isChecked()){ //sd card
					saveExternal(content.getText().toString());
				}else{ // internal card
					saveInternal(content.getText().toString());
				}
				
			}
			
		});
		
		load=(Button)findViewById(R.id.btn_load);
		load.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(check.isChecked()){ //sd card
					content.setText(loadExternal());
				}else{ // internal card
					content.setText(loadInternal());
				}
				
			}
			
		});
		
		exit=(Button)findViewById(R.id.btn_exit);
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}
	
	private void saveExternal(String str){//sd card save
		String state = Environment.getExternalStorageState(); // Environment : 환경 객체 
		
		try{
			if(state.equals(Environment.MEDIA_MOUNTED)){//sd card in
				File dir = Environment.getExternalStorageDirectory();
				File file = new File(dir, FILENAME);
				FileOutputStream fos = new FileOutputStream(file);
				Writer writer = new OutputStreamWriter(fos,"UTF-8");
				
				writer.write(str);
				writer.close();
			}else{
				Toast.makeText(this, "SD 카드 미 장착", Toast.LENGTH_LONG).show(); // LENGTH_LONG : 토스트 메시지 5초간 생성
			}
		}catch(Exception e){
			Toast.makeText(this, "SD 카드에 저장 도중 문제 발생", Toast.LENGTH_LONG).show();
		}
	}
	
	private void saveInternal(String str){//Internal card save
		FileOutputStream fos = null;
		
		try{
			fos = openFileOutput(FILENAME,MODE_PRIVATE);
			Writer writer = new OutputStreamWriter(fos,"UTF-8");
			
			writer.write(str);
			writer.close();
		}catch(Exception e){
			Toast.makeText(this, "내장 메모리에 저장 도중 문제 발생", Toast.LENGTH_LONG).show();
		}
	}
	
	private String loadInternal(){
		
		FileInputStream fis = null;
		String value = null;
		
		try{
			fis = openFileInput(FILENAME);
			
			Reader reader = new InputStreamReader(fis,"UTF-8");
			int size = fis.available();
			char[] buffer = new char[size];
			
			reader.read(buffer);
			reader.close();
			
			value=new String(buffer);
		}catch(Exception e){
			Toast.makeText(this, "SD 카드 로드 중 문제 발생", Toast.LENGTH_LONG).show();
		}
		
		return value;
	}
	
	private String loadExternal(){
		String state = Environment.getExternalStorageState();
		FileInputStream fis = null; 
		String value = null;
		
		try{
			if(state.equals(Environment.MEDIA_MOUNTED)){
				File dir = Environment.getExternalStorageDirectory();
				File file = new File(dir,FILENAME);
				fis = new FileInputStream(file);
				Reader reader = new InputStreamReader(fis,"UTF-8");
				int size = fis.available();
				char[] buffer = new char[size];
				
				reader.read(buffer);
				reader.close();
				
				value= new String(buffer);
			}else{
				Toast.makeText(this, "SD 카드 미장착", Toast.LENGTH_LONG).show();
			}
		}catch(Exception e){
			Toast.makeText(this, "내장 메모리 카드 로드 중 문제 발생", Toast.LENGTH_LONG).show();
		}
		return null;
	}
}
