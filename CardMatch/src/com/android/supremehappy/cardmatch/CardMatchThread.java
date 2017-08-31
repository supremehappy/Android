package com.android.supremehappy.cardmatch;

public class CardMatchThread extends Thread {

	CardGame cg;
	
	public CardMatchThread(CardGame cg){
		this.cg=cg;
	}
	
	@Override
	public void run() {
		
		while(true){
			cg.checkMatch(); 
		}
	}

}
