package com.android.supremehappy.cardmatch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class CardGame extends View {

	Bitmap m_backgroundImage; // 배경이미지
	Bitmap m_card_back; // 카드 뒷면
	Bitmap m_card_red;
	Bitmap m_card_green;
	Bitmap m_card_blue;
	Bitmap m_card_white;
	Card[][] m_shuffle; // 카드 배열
	
	public static final int STATE_READY=0; // 시작전
	public static final int STATE_GAME=1; // 게임 중
	public static final int STATE_END=2; // 게임 끝
	
	private int m_state = STATE_READY;
	private Card m_select1 = null; // 첫번째 선택한 카드
	private Card m_select2 = null; // 두번째 선택한 카드
	
	public CardGame(Context context) {
		super(context);
		m_backgroundImage=BitmapFactory.decodeResource(getResources(), R.drawable.background);
		m_card_back=BitmapFactory.decodeResource(getResources(), R.drawable.backside);
		m_card_red=BitmapFactory.decodeResource(getResources(), R.drawable.front_red);
		m_card_green=BitmapFactory.decodeResource(getResources(), R.drawable.front_green);
		m_card_blue=BitmapFactory.decodeResource(getResources(), R.drawable.front_blue);
		m_card_white=BitmapFactory.decodeResource(getResources(), R.drawable.front_white);
		
		m_shuffle=new Card[4][2];
		
		cardShuffle();
		
		CardMatchThread cmt = new CardMatchThread(this);
		
		cmt.start();
	}
	
	public void cardShuffle(){
		m_shuffle[0][0]= new Card(Card.IMG_GREEN);
		m_shuffle[0][1]= new Card(Card.IMG_BLUE);
		m_shuffle[1][0]= new Card(Card.IMG_RED);
		m_shuffle[1][1]= new Card(Card.IMG_BLUE);
		m_shuffle[2][0]= new Card(Card.IMG_GREEN);
		m_shuffle[2][1]= new Card(Card.IMG_RED);
		m_shuffle[3][0]= new Card(Card.IMG_WHITE);
		m_shuffle[3][1]= new Card(Card.IMG_WHITE);
	}
	
	public void startGame(){ // 겜 실행
		// 겜 시작하면 카드 뒤집어짐
		m_shuffle[0][0].m_state=Card.CARD_CLOSE;
		m_shuffle[0][1].m_state=Card.CARD_CLOSE;
		m_shuffle[1][0].m_state=Card.CARD_CLOSE;
		m_shuffle[1][1].m_state=Card.CARD_CLOSE;
		m_shuffle[2][0].m_state=Card.CARD_CLOSE;
		m_shuffle[2][1].m_state=Card.CARD_CLOSE;
		m_shuffle[3][0].m_state=Card.CARD_CLOSE;
		m_shuffle[3][1].m_state=Card.CARD_CLOSE;
	}

	public void checkMatch(){ // 색 일치 판단
		if(m_select1 == null || m_select2 ==null){ 
			return;
		}
		if(m_select1.m_color==m_select2.m_color){ // 일치
			m_select1.m_state=Card.CARD_MATCHED; // 상태 변경
			m_select2.m_state=Card.CARD_MATCHED;
			m_select1=null;
			m_select2=null;
		}else{
			try{
				Thread.sleep(500); // 0.5초 뒤에 뒤집음
			}catch(Exception e){}
				m_select1.m_state=Card.CARD_CLOSE;
				m_select2.m_state=Card.CARD_CLOSE;
				m_select1=null;
				m_select2=null;
			
		}
		postInvalidate();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(m_backgroundImage, 0, 0,null);// 배경이미지 출력
		
		for(int y = 0; y<2; y++){
			for(int x=0; x<4; x++){
				if(m_shuffle[x][y].m_state == Card.CARD_SHOW || m_shuffle[x][y].m_state == Card.CARD_PLAYEROPEN||m_shuffle[x][y].m_state==Card.CARD_MATCHED){
					if(m_shuffle[x][y].m_color == Card.IMG_BLUE){
						canvas.drawBitmap(m_card_blue, 30+x*250, 460+y*330,null);
					}else if(m_shuffle[x][y].m_color == Card.IMG_RED){
							canvas.drawBitmap(m_card_red, 30+x*250, 460+y*330,null);
					}else if(m_shuffle[x][y].m_color == Card.IMG_GREEN){
						canvas.drawBitmap(m_card_green, 30+x*250, 460+y*330,null);
					}else if(m_shuffle[x][y].m_color == Card.IMG_WHITE){
						canvas.drawBitmap(m_card_white, 30+x*250, 460+y*330,null);
					}
				}else{
					canvas.drawBitmap(m_card_back, 30+x*250, 460+y*330,null);
				}
			}
		}
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		if(m_state == STATE_READY){
			startGame(); // 겜시작
			m_state = STATE_GAME; // 게임중으로 상태전환 
		}else if(m_state == STATE_GAME){ // 선택한 카드 찾기
			int px = (int)event.getX();//터치한 x좌표
			int py = (int)event.getY();//터치한 y좌표
			
			for(int y =0; y<2; y++){
				for(int x =0; x<4; x++){ // 터치한 좌표(가상카드)와 모든 카드 좌표 비교
					Rect box_card = new Rect(30+x*250,450+y*330,
							30+x*250+(m_card_red.getWidth()),
							450+y*330+(m_card_red.getHeight()));//Rect(,,,)
					
					if(box_card.contains(px,py)){
						if(m_shuffle[x][y].m_state != Card.CARD_MATCHED){ // 짝 안 맞는 경우
							if(m_select1 == null){ // 첫번째 선택
								m_select1 = m_shuffle[x][y];
								m_shuffle[x][y].m_state = Card.CARD_PLAYEROPEN;
							}else{ // 두번째 선택
								if(m_select1 != m_shuffle[x][y]){ // 같은 카드 선택 하지 않은 경우
									m_select2 = m_shuffle[x][y];
									m_shuffle[x][y].m_state = Card.CARD_PLAYEROPEN;
								}
							}
						}else{
							
						}
					}
				}
			}
		}else if(m_state == STATE_END){
			
		}
		
		invalidate();
		return super.onTouchEvent(event);
	}

}