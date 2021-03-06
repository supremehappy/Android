package com.android.supremehappy.menutest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;

public class ChronoActivity extends Activity {

	Chronometer chrono;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chrono);
		
		chrono = (Chronometer) findViewById(R.id.chronometer1);
		registerForContextMenu(chrono);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {

		this.getMenuInflater().inflate(R.menu.context, menu);
		
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case R.id.start: 
			chrono.start(); 
			break;
		case R.id.stop: 
			chrono.stop(); 
			break;
		case R.id.reset:
			chrono.setBase(SystemClock.elapsedRealtime());
			
			break;
			
		}
			
		return super.onContextItemSelected(item);
	}

}
