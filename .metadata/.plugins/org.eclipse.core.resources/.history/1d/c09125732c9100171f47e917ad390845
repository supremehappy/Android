package com.android.supremehappy.bubbleshouting;

import java.util.ArrayList;
import java.util.Random;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;

public class MyGameView extends SurfaceView implements Callback {
	
	GameThread gt;
	SurfaceHolder sh;
	SoundPool m_sound_pool;
	int m_sound_id1,m_sound_play_id1;
	Activity a;
	MediaPlayer m_sound_background;
	Context mContext;
	
	public MyGameView(Context context) {
		super(context);
		
		sh = getHolder();
		sh.addCallback(this);
		
		gt = new GameThread(sh, context);
		
		mContext=context;
		a= (Activity)mContext;
		m_sound_pool= new SoundPool(5,AudioManager.STREAM_MUSIC,0);
		m_sound_id1 = m_sound_pool.load(context, R.raw.bubble,1);
		m_sound_background= MediaPlayer.create(mContext, R.raw.sctt1);
		
		m_sound_background.start();
		
		if(m_sound_background.isPlaying()){
			m_sound_background.setLooping(true);	
		}
	}
	
	public void stopGame(){
		gt.stopThread();
	}
	
	public void pauseGame(){
		gt.pauseNResume(true);
	}
	
	public void resumeGame(){
		gt.pauseNResume(false);
	}
	
	public void restartGame(){
		gt.stopThread();
		gt=null;
		gt=new GameThread(sh,mContext);
		gt.start();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		gt.start();

	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		boolean done = true;
		
		// 스레드가 종료되길 기다리다 서피스 뷰 삭제
		while(done){
			
			try{
				gt.join(); //스레드 종료되길 대기
				done = false; // 스레드 종료되면 반복 종료
			}catch(Exception e){
				
			}
			
		}

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_DOWN){
			
			// SurfaceHolder 스레드 동기화 -> 풍선 생성 후 동기화 해제
			synchronized (sh) {
			int x= (int)event.getX();
			int y= (int)event.getY();
			
//			gt.makeBUbble(x, y);
			gt.makeWaterBall(); // 탄환생성
			}
			
		}
		return super.onTouchEvent(event);
	}
	
	//-------------------------------- 내부 스레드 클래스
	class GameThread extends Thread{
		
		SurfaceHolder gh;
		Context gc;
		int width, height;
		Bitmap imgBack;
		
		Bitmap imgSpider; // 거미 이미지 변수
		int sw, sy; // 거미 크기
		int mx, my; // 거미 좌표
		long lastTime; // 시간 계산
		
		ArrayList<Bubble> mBall = new ArrayList<Bubble>();
		ArrayList<SmallBall> sBall = new ArrayList<SmallBall>();
		ArrayList<WaterBall> wBall = new ArrayList<WaterBall>();
		
		int tot = 0; // 점수 합계
		Paint paint = new Paint();
		
		ArrayList<Score> mScore = new ArrayList<Score>();
		
		boolean canRun = true; // 스레드 종료 여부 
		boolean isWait = false; // 스레드 대기 여부
		
		GameThread(SurfaceHolder sh, Context context){
			gh = sh;
			gc = context;
			
			Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			
			width = display.getWidth();
			height = display.getHeight();
			
			imgBack = BitmapFactory.decodeResource(getResources(), R.drawable.sky);
			imgBack = Bitmap.createScaledBitmap(imgBack, width, height, false);
			
			imgSpider = BitmapFactory.decodeResource(getResources(), R.drawable.spider1);
			
			sw= imgSpider.getWidth()/2;
			sy =imgSpider.getHeight()/2;
			mx=width/2;
			my=height-200;
			
			paint.setAntiAlias(true);
			paint.setTextSize(50);
			paint.setColor(Color.WHITE);
			
			setFocusable(true);
		}
		
		public void run(){
			Canvas canvas = null;
			
			while(canRun){
				canvas = gh.lockCanvas();
				
				try{
					synchronized (gh) {
						
						makeBubble();// 풍선 임의 생성
						moveCharacters(); 
						checkCollision();
						drawCharacter(canvas);
						
						/*moveBubble();
						canvas.drawBitmap(imgBack, 0, 0, null);
						
						for(Bubble bubble : mBall){ // arraylist 에 담긴 풍선들 다 그림
							canvas.drawBitmap(bubble.imgBall, bubble.x-bubble.rad, bubble.y-bubble.rad, null);
						}
						for(SmallBall bubble : sBall){ // arraylist 에 담긴 작은 풍선들 다 그림
							canvas.drawBitmap(bubble.imgBall, bubble.x-bubble.rad, bubble.y-bubble.rad, null);
						}*/
					}
				}finally{
					if(canvas != null){
						gh.unlockCanvasAndPost(canvas);
					}
				}
				
				synchronized (this) {
					if(isWait){
						try{
							wait();
						}catch(Exception e){
							
						}
					}
				}
			}
		}
		
		public void stopThread(){ // 스레드 종료
			canRun = false;
			synchronized (this) { 
				this.notify(); 
			}
		}
		
		public void pauseNResume(boolean wait){ // 스레드 정지/재시작
			isWait = wait;
			synchronized (this) {
				this.notify();
			}
		}
		
		// 풍선 임의 생성 메서드
		public void makeBubble(){
			Random rnd = new Random();
			
			if(mBall.size() >100 || rnd.nextInt(40)<38)
				return;
			
			int x= -50;
			int y= rnd.nextInt(201)+50;
			
			mBall.add(new Bubble(mContext,x,y,width,height));
			 
		}
		
		//탄환 생성 메서드
		public void makeWaterBall(){
			long thisTime = System.currentTimeMillis();
			
			if(thisTime - lastTime >= 300){ // 1/3초에 하나 발사
				wBall.add(new WaterBall(mContext ,mx, my, width, height));
			}
			lastTime = thisTime; 
		}
		
		//탄환이 풍선에 맞았는지 체크 메서드
		public void checkCollision(){
			int x1, y1, x2, y2;
			
			for(WaterBall water : wBall){ // 모든 탄환 대상
				// 각 탄환 좌표 구함
				x1 = water.x;
				y1 = water.y;
				
				for(Bubble tmp : mBall){ // 모든 풍선 대상
					// 각 풍선 좌표 구함
					x2 = tmp.x; 
					y2 = tmp.y;
					
					if(Math.abs(x1-x2)<tmp.rad && Math.abs(y1-y2) <tmp.rad){ // 탄환, 풍선의 좌표 차이가 풍선 반지름 안에 있으면 충돌
						makeSmallBubble(tmp.x,tmp.y); // 작은 풍선 생성
						
						mScore.add(new Score(tmp.x, tmp.y));
						
						tot = tot+100;
						
						//각 상태 변경
						tmp.dead = true;
						water.dead= true;
						m_sound_play_id1=m_sound_pool.play(m_sound_id1, 1, 1, 0, 0, 1);
						break; 
					}
				}
			}
		}
		
		// 모든 캐릭터 생성 메서드
		public void drawCharacter(Canvas canvas){
			canvas.drawBitmap(imgBack, 0, 0, null); // 배경
			
			for(Bubble tmp: mBall){
				canvas.drawBitmap(tmp.imgBall, tmp.x-tmp.rad,tmp.y-tmp.rad, null);
			}
			for(SmallBall tmp : sBall){
				canvas.drawBitmap(tmp.imgBall, tmp.x-tmp.rad,tmp.y-tmp.rad, null);
			}
			for(WaterBall tmp : wBall){
				canvas.drawBitmap(tmp.imgBall, tmp.x-tmp.rad,tmp.y-tmp.rad, null);
			}
			for(Score tmp : mScore){
				canvas.drawText("+100", tmp.x-20, tmp.y-10, tmp.paint);
			}
			canvas.drawText("total : "+tot, 10, 30, paint);
			canvas.drawBitmap(imgSpider, mx-sw, my-sy, null);
		}
		
		//모든 캐릭터를 움직이는 메서드
		public void moveCharacters(){
			for(int i = mBall.size()-1; i>=0; i--){
				mBall.get(i).moveBubble();
				
				if(mBall.get(i).dead==true){
					mBall.remove(i);
				}
			}
			
			for(int i = sBall.size()-1; i>=0; i--){
				sBall.get(i).moveBall();
				
				if(sBall.get(i).dead==true){
					sBall.remove(i);
				}
			}
			
			for(int i = wBall.size()-1; i>=0; i--){
				wBall.get(i).moveBall();
				
				if(wBall.get(i).dead==true){
					wBall.remove(i);
				}
			}
			
			for(int i = mScore.size()-1; i>=0; i--){
				if(mScore.get(i).move()==false){
					mScore.remove(i);
				}
			}
		}
		
		// 풍선 이동 메서드(스레드에서 이용)
		public void moveBubble(){
			for(int i=mBall.size()-1; i>=0; i--){
				mBall.get(i).moveBubble();// 풍선 이동
				
				if(mBall.get(i).dead==true){ // 풍선 상태가 dead = true 면
					
					makeSmallBubble(mBall.get(i).x,mBall.get(i).y);
					mBall.remove(i); // arraylist 에서 제거
				}
			}
			
			for(int i = sBall.size()-1; i>=0; i--){
				sBall.get(i).moveBall(); // 작은 풍선 이동
				if(sBall.get(i).dead==true){// 작은 풍선 상태가 dead = true 면
					sBall.remove(i); // arraylist 에서 제거
				}
			}
		}
		
		// 풍선 생성 및 터치 반응 메서드
		public void makeBUbble(int x, int y){
			boolean flag = false;
			
			for(Bubble bubble : mBall){
				if(Math.pow(bubble.x-x, 2)+Math.pow(bubble.y-y, 2)<=Math.pow(bubble.rad,2)){ // 풍선 터치시 
					bubble.dead = true; // 해당 풍선 상태 변경
					
					flag=true; // 터치로 설정
					
				}
			}
			
			if(flag==false){
				mBall.add(new Bubble(gc,x,y,width,height)); // 풍선 생성 후 arraylist 에 저장
			}
		}
		
		//작은 풍선 생성
		public void makeSmallBubble(int x, int y){
			Random rnd = new Random();
			int count = rnd.nextInt(9)+7;
			
			for(int i =1 ; i<count; i++){
				int ang = rnd.nextInt(360);
				sBall.add(new SmallBall(gc,x,y,ang,width,height));
			}
		}
	}
}
