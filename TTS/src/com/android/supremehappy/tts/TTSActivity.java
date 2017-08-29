package com.android.supremehappy.tts;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TTSActivity extends Activity implements TextToSpeech.OnInitListener{

	private static final String[] WORDS={"왈왈", "멍멍", "나야나", "뭐", "이런거 처음 보냐?", "망했어요"};
	private static final Random RAND = new Random();
	private TextToSpeech mTTS;
	private Button btn_tts;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tts);
		
		tv = (TextView) findViewById(R.id.textView9);
		mTTS = new TextToSpeech(this,this); // TextToSpeech(this,this) : 첫 this = tts 가 동작되는 액티비티, 두번째 this  = tts 리스터 위치
		
		btn_tts = (Button) findViewById(R.id.btn_tts);
		btn_tts.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				sayHello();
				
			}
			
		});
	}

	@Override
	public void onInit(int status) { //tts 초기화 메서드
		
		if(status == TextToSpeech.SUCCESS){
			int result = mTTS.setLanguage(Locale.KOREAN);//한국어
			
			if(result!=TextToSpeech.LANG_MISSING_DATA || result != TextToSpeech.LANG_NOT_SUPPORTED){// 한국어 정보가 있고 지원하는 언어일때
				btn_tts.setEnabled(true);// 버튼 활성화
				sayHello();
			}else{
				Toast.makeText(this, "not TTS", Toast.LENGTH_SHORT).show();
				btn_tts.setEnabled(false);
			}
		}
		
	}
	
	private void sayHello(){
		
		String word = "에러";
		AssetManager assetManager = this.getAssets();
		InputStream is = null;
		
		try{
			is = assetManager.open("txt/text.txt");
			String text = loadTextFile(is);
			tv.setText(text);
			mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
		}catch(Exception e){
			mTTS.speak(word, TextToSpeech.QUEUE_FLUSH, null);
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
