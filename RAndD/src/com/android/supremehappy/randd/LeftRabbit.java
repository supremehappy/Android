package com.android.supremehappy.randd;

public class LeftRabbit extends Thread {

	RAndDView rv;
	
	LeftRabbit(RAndDView rv){
		this.rv=rv;
	}
	@Override
	public void run() {
		
		int terminate = 120, current =0;
		
		while(current < terminate){
			int rnd = (int)(Math.random()*6);
			
			if(rv.flag!=0){
				
			}else{
				current=current+rnd;
				rv.r1_y=current;
			}
			
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}
		
		synchronized(rv.flag){
			if(rv.flag==0){
				rv.flag=1;
				rv.name="왼_토";
				rv.postInvalidate();
			}
		}
		
	}

}