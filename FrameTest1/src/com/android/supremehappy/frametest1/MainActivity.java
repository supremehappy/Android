package com.android.supremehappy.frametest1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

	boolean isPageOpen = false, isPageOpen1 = false;
	Button btn_open,btn_open1;
	Animation toLeft, toRight,toLeft1, toRight1;
	LinearLayout layout, layout1;
	
	int a;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SlidingListener sl = new SlidingListener();
		
		layout = (LinearLayout) findViewById(R.id.my_layout);
		layout1 = (LinearLayout) findViewById(R.id.my_layout1);
		
		toLeft = AnimationUtils.loadAnimation(this, R.anim.move_left);
		toLeft.setAnimationListener(sl);
		toRight = AnimationUtils.loadAnimation(this, R.anim.move_right);
		toRight.setAnimationListener(sl);
		
		toLeft1 = AnimationUtils.loadAnimation(this, R.anim.move_left_1);
		toLeft1.setAnimationListener(sl);
		toRight1 = AnimationUtils.loadAnimation(this, R.anim.move_right_1);
		toRight1.setAnimationListener(sl);
		
		
		btn_open = (Button)findViewById(R.id.btn_open);
		btn_open.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				a=1;
				if(isPageOpen){
					layout.startAnimation(toRight);
					
				}else{
					layout.setVisibility(View.VISIBLE);
					layout.startAnimation(toLeft);
				}
				
			}
			
		});
		
		btn_open1 = (Button)findViewById(R.id.btn_open1);
		btn_open1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				a=2;
				if(isPageOpen1){
					Toast.makeText(MainActivity.this, "toLeft1", Toast.LENGTH_SHORT).show();
					layout1.startAnimation(toLeft1);
				}else{
					Toast.makeText(MainActivity.this, "toRight1", Toast.LENGTH_SHORT).show();
					layout1.setVisibility(View.VISIBLE);
					layout1.startAnimation(toRight1);
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

			if(a==1){
				if(isPageOpen){
					layout.setVisibility(View.INVISIBLE); // 화면에서 사라짐
					btn_open.setText("open!!!");
					isPageOpen =false;
				}else{
					btn_open.setText("close!!!");
					isPageOpen =true;
				}
			}else if(a==2){
				if(isPageOpen1){
					layout1.setVisibility(View.INVISIBLE); // 화면에서 사라짐
					btn_open1.setText("open!!!");
					isPageOpen1 =false;
				}else{
					btn_open1.setText("close!!!");
					isPageOpen1 =true;
					
				}
			}
		}

		@Override
		public void onAnimationRepeat(Animation animation) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
