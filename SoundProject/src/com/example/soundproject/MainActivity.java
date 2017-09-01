package com.example.soundproject;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	MediaPlayer m_sound_background,m_sound_background1;
	SoundPool m_sound_pool;
	Button btn1, btn2, btn3, btn4,exit;
	TextView result;
	int m_sound_id1,m_sound_id2, m_sound_play_id1,m_sound_play_id2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		m_sound_background= MediaPlayer.create(this, R.raw.sctt1);
		m_sound_background1= MediaPlayer.create(this, R.raw.tga);
		m_sound_pool= new SoundPool(5,AudioManager.STREAM_MUSIC,0);//SoundPool(동시재생 가능한 최대 오디오 스트림 갯수,AudioManager.STREAM_MUSIC:오디오 스트림의 종류,음질 수준);
		
		m_sound_id1 = m_sound_pool.load(this, R.raw.effect1,1); //load(액티비티, 리소스,우선순위)
		m_sound_id2 = m_sound_pool.load(this, R.raw.effect2,1);
		
		result=(TextView)findViewById(R.id.result);
		
		btn1=(Button)findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				m_sound_play_id1=m_sound_pool.play(m_sound_id1, 1, 1, 0, 0, 1);//play(재생되는 스트림 번호, 왼쪽 스피커 볼륨(0.0~1.0), 오른쪽 스피커 볼륨(0.0~1.0), 우선순위, 반복여부(0:반복x/ -1:무한반복), 재생속도(1:정상/2:2배/0.5:2배느림)0.0~2.0);
				
			}
			
		});

		btn2=(Button)findViewById(R.id.button2);
		btn2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				m_sound_play_id2=m_sound_pool.play(m_sound_id2, 1, 1, 0, 0, 1);
				
			}
			
		});

		btn3=(Button)findViewById(R.id.button3);
		btn3.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(m_sound_background.isPlaying()){
					m_sound_background.pause();
					result.setText("배경음악 중지");
				}else{
					m_sound_background.start();
					result.setText("배경음악 재생");
				}
				
			}
			
		});
		
		btn4=(Button)findViewById(R.id.button4);
		btn4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(m_sound_background1.isPlaying()){
					m_sound_background1.pause();
					result.setText("배경음악 중지");
				}else{
					m_sound_background1.start();
					result.setText("배경음악 재생");
				}
				
			}
			
		});

		exit=(Button)findViewById(R.id.button5);
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
			
		});
	}

	@Override
	protected void onDestroy() {
		m_sound_background.stop();
		m_sound_background.release();
		m_sound_pool.stop(m_sound_play_id1);
		m_sound_pool.stop(m_sound_play_id2);
		super.onDestroy();
	}
	
}