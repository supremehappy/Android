package com.android.supremehappy.randd;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

public class MainActivity extends Activity implements TextToSpeech.OnInitListener{

	private TextToSpeech mTTS;
	RAndDView rnd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		mTTS = new TextToSpeech(this,this);
		setContentView(new RAndDView(this,mTTS));
		
	}

	@Override
	public void onInit(int status) {
		
		if(status == TextToSpeech.SUCCESS){
			int result = mTTS.setLanguage(Locale.KOREAN);//한국어
			
			if(result!=TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED){// 한국어 정보가 있고 지원하는 언어일때
				
				mTTS.speak("start", TextToSpeech.QUEUE_FLUSH, null);
			}else{
				Toast.makeText(this, "not TTS", Toast.LENGTH_SHORT).show();
				
			}
		}
	}
}
