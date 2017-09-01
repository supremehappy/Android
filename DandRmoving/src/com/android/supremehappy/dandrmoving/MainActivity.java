package com.android.supremehappy.dandrmoving;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	MyDragon mg;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyDragon(this));
	}

	@Override
	protected void onPause() {
		mg.stopDragon();
		super.onPause();
	}
}