package com.android.supremehappy.elebator;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class RabbitActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RabbittView(this));
		
		//상태바 제거
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//제목바 제거
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	class RabbittView extends View{
		int width, height, x,y, sx,sy, rw,rh, counter=0,n;
		Bitmap[] rabbits = new Bitmap[2];
		
		Handler mHandler = new Handler(){
			public void handleMessage(Message msg){
				invalidate(); // onDraw() 메서드 호출 하는 객체가 View 와 같은 객체 일 경우 / 아닐경우 : postInvalidate()
				mHandler.sendEmptyMessageDelayed(0, 10); //0.01초 후 다시 핸들러 호출
			}
		};
		
		public RabbittView(Context context) {
			super(context);
			
			Display display= ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			
			width=display.getWidth();
			height = display.getHeight();
			
			rabbits[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit_1);
			rabbits[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.rabbit_2);
			
			rw=rabbits[0].getWidth()/2;
			rh=rabbits[1].getWidth()/2;
			
			x=y=100; // 시작 좌표
			sx=sy=3; // 이동(우하방향)			
			
			mHandler.sendEmptyMessageDelayed(0, 10);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			x = x + sx; 
			y = y + sy;
			counter++;
			n = (counter % 20) / 10;
			
			if(x < rw){//왼쪽
				x = rw; 
				sx = -sx;
			}
			if(x > width - rw){//오른쪽
				x = width - rw; 
				sx = -sx;
			}
			if(y < rh){//천장
				y = rh; 
				sy = -sy;
			}
			if(y > height - rh){//바닥
				y = height - rh; 
				sy = -sy;
			}
			canvas.drawBitmap(rabbits[n],x - rw, y-rh, null);
		}
		
	}
}