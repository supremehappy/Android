package com.example.sensortest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new SersorView(this));
	}
	
	class SersorView extends View implements SensorEventListener{

		int m_x = 0, m_y=0;
		SensorManager sensorManager; 
		String m_str;
		
		public SersorView(Context context) {
			super(context);
			
			sensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
			/*
			 * SENSOR_DELAY_FASTEST : 가장 빠른 센서업데이트 속도
				SENSOR_DELAY_GAME : 게임 제어에 적합한 속도
				SENSOR_DELAY_NOMAL : 기본 속도
				SENSOR_DELAY_UI :  화면처리에 적합한 속도
			 * */ 
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),sensorManager.SENSOR_DELAY_GAME);
			// registerListener(처리할 리스너 위치 ,방향 ,속도);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			Bitmap img = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
			
			canvas.drawBitmap(img, m_x, m_y, null);
			
			Paint p = new Paint();
			
			p.setTextSize(30);
			p.setColor(Color.BLACK);
			canvas.drawText(m_str, 0, 20, p);
		}

		@Override
		public void onSensorChanged(SensorEvent event) { // 센서값 바뀌면 자동호출되는 메서드
			synchronized (this) {
				switch (event.sensor.getType()) {
				case Sensor.TYPE_ORIENTATION: // 센서 종류가 방향센서 일때
					float heading = event.values[0]; // 0~360 값(기기를 바닥에 놓은 채로 돌릴때) 
					// pitch, roll 은 평지에서 0
					float pitch = event.values[1]; // 단말기가 하늘을 향할때 : -90 / 바닥에 있을때 180 / 뒤집혀 있을때 : -180
					float roll = event.values[2]; // 좌우 기울임에 따라 -90 ~ 90
					
					m_str = "방향 데이터 : ";
					m_str = m_str + "Heading : "+ String.valueOf(heading);
					m_str = m_str + "Pitch : "+ String.valueOf(pitch);
					m_str = m_str + "Roll : "+ String.valueOf(roll);
					
					//이미지 좌표
					m_x = m_x - (int)roll;
					m_y = m_y - (int)pitch;
					
					// 좌표가 음수값 갖지 못하도록 방지
					if(m_x <= 0){
						m_x = 0;
					}
					if(m_y <= 0){
						m_y = 0;
					}
					
					// 50 은 안드로이드 이미지 크기 / 화면에서 벗어나지 않도록 방지
					if(m_x >= (getWidth()-50)){
						m_x = getWidth()-50;
					}
					if(m_y >= (getHeight()-50)){
						m_y = getHeight()-50;
					}
					
					break;
				}
			}
			invalidate();
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) { 
			// TODO Auto-generated method stub
			
		}
		
	}
}
