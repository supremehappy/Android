package com.android.supremehappy.cardmatch;

public class Card {

	public static final int CARD_SHOW = 0; // 카드 앞면 
	public static final int CARD_CLOSE = 1; // 카드 뒷면
	public static final int CARD_PLAYEROPEN = 2; // 게이머가 카드 뒤집어서 앞면
	public static final int CARD_MATCHED = 3; // 짝 맞춘 상태
	
	public static final int IMG_RED=1; // 적색카드
	public static final int IMG_GREEN=2; 
	public static final int IMG_BLUE=3;
	public static final int IMG_WHITE=4;
	
	public int m_state; // 카드 상태
	public int m_color; // 카드 색
	
	public Card(int color){
		m_state = CARD_SHOW; // 카드 초기상태 설정
		m_color = color; // 카드 색 설정
	}
	
}


