package com.android.supremehappy.rabbitracing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class RabbitView extends View {

	public int r1_y, r2_y; // 토끼 y 좌표
	Integer flag = 0; // 왼쪽 토끼가 먼저 도착 하면 1, 오른쪽 토끼가 먼저 도착하면 2
	String name;
	Bitmap[] rabbit1=new Bitmap[2];//왼토
	Bitmap[] rabbit2=new Bitmap[2];//오토
	
	int counter, order;
	
	public RabbitView(Context context) {
		super(context);
		rabbit1[0]=BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_1);
		rabbit1[1]=BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_2);
		rabbit2[0]=BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_1);
		rabbit2[1]=BitmapFactory.decodeResource(getResources(), R.drawable.rabbit_2);
		
		LeftRabbit lr = new LeftRabbit(this);
		RightRabbit rr = new RightRabbit(this);
		
		lr.start();
		rr.start();
		
		h.sendEmptyMessageDelayed(0, 10);
	}
	
	Handler h = new Handler(){
		public void handleMessage(Message msg){
			invalidate();
			h.sendEmptyMessageDelayed(0, 10);
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		counter++;
		order = (counter%20)/10;
		canvas.drawBitmap(rabbit1[order], 100, 1300-(r1_y*20),null);
		canvas.drawBitmap(rabbit2[order], 450, 1300-(r2_y*20),null);
		
		if(flag==1){
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setTextSize(50);
			paint.setColor(Color.BLUE);
			canvas.drawText(name+"가 먼저 도착", 100, 50, paint);
		}else if(flag==2){
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			paint.setTextSize(50);
			paint.setColor(Color.BLUE);
			canvas.drawText(name+"가 먼저 도착", 100, 50, paint);
		}
	}
	
	

}

