package com.android.supremehappy.rabbitracing;

public class RightRabbit extends Thread {

	RabbitView rv;
	
	RightRabbit(RabbitView rv){
		this.rv=rv;
	}
	@Override
	public void run() {
		int terminate = 100, current =0;
		
		while(current < terminate){
			int rnd = (int)(Math.random()*6);
			
			if(rv.flag!=0){
				
			}else{
				current=current+rnd;
				rv.r2_y=current;
			}
			
			try{
				Thread.sleep(100);
			}catch(Exception e){
				
			}
		}
		
		synchronized(rv.flag){
			if(rv.flag==0){
				rv.flag=2;
				rv.name="오_토";
				rv.postInvalidate();
			}
		}
	}
}
