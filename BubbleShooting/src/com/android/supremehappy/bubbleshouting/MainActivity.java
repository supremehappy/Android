package com.android.supremehappy.bubbleshouting;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;

public class MainActivity extends Activity {
	
	MyGameView mgv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mgv= new MyGameView(this);
		
		setContentView(mgv);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0,1,0,"게임종료");
		menu.add(0,2,0,"일시정지");
		menu.add(0,3,0,"계속진행");
		menu.add(0,4,0,"게임초기화");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case 1 :
			mgv.stopGame();
			finish();
			break;
		case 2 :
			mgv.pauseGame();
			break;
		case 3 :
			mgv.resumeGame();
			break;
		case 4 :
			mgv.restartGame();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		mgv.resumeGame();
		super.onResume();
	}

	@Override
	protected void onPause() {
		mgv.pauseGame();
		super.onPause();
	}

	@Override
	protected void onStop() {
		mgv.stopGame();
		super.onStop();
	}
}