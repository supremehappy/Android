package com.android.supremehappy.rabbitracing;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	Bundle myBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.myBundle=savedInstanceState;
		super.onCreate(savedInstanceState);
		
		setContentView(new RabbitView(this));
			
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.rabbit_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch(item.getItemId()){
		case R.id.exit :
			finish();
			break;
		case R.id.restart :
			this.onCreate(myBundle);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
}

