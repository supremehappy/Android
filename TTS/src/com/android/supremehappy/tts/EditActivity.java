package com.android.supremehappy.tts;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends Activity implements TextToSpeech.OnInitListener{

	Button btn_edit;
	EditText edit_txt;
	TextView txt_view1;
	private TextToSpeech mTTS;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		
		mTTS = new TextToSpeech(this,this);
		
		edit_txt = (EditText)findViewById(R.id.edit_txt);
		txt_view1 = (TextView)findViewById(R.id.txt_view1);
		
		btn_edit = (Button)findViewById(R.id.btn_edit);
		btn_edit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				String str = edit_txt.getText().toString();
				
				txt_view1.setText(str);
				mTTS.speak(str, TextToSpeech.QUEUE_FLUSH, null);
				
			}
			
		});
	}

	@Override
	public void onInit(int status) { //tts 초기화 메서드
		
		if(status == TextToSpeech.SUCCESS){
			int result = mTTS.setLanguage(Locale.KOREAN);//한국어
			
			if(result!=TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED){// 한국어 정보가 있고 지원하는 언어일때
				
			}else{
				Toast.makeText(this, "not TTS", Toast.LENGTH_SHORT).show();
				
			}
		}
		
	}
}