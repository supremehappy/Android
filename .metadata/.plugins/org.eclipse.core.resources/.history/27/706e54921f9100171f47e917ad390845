package com.android.supremehappy.framtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	boolean isPageOpen = false;
	Button btn_open;
	Animation toLeft, toRight;
	LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SlidingListener sl = new SlidingListener();
		
		layout = (LinearLayout) findViewById(R.id.my_layout);
		
		toLeft = AnimationUtils.loadAnimation(this, R.anim.move_left);
		toLeft.setAnimationListener(sl);
		toRight = AnimationUtils.loadAnimation(this, R.anim.move_right);
		toRight.setAnimationListener(sl);
		
		btn_open = (Button)findViewById(R.id.btn_open);
		btn_open.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(isPageOpen){
					layout.startAnimation(toRight);
					
				}else{
					layout.setVisibility(View.VISIBLE);
					layout.startAnimation(toLeft);
				}
				
			}
			
		});
	}
	
	class SlidingListener implements Animation.AnimationListener{

		@Override
		public void onAnimationStart(Animation animation) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationEnd(Animation animation) {

			if(isPageOpen){
				layout.setVisibility(View.INVISIBLE); // 화면에서 사라짐
				btn_open.setText("open!!!");
				isPageOpen =false;
			}else{
				btn_open.setText("close!!!");
				isPageOpen =true;
			}
			
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
