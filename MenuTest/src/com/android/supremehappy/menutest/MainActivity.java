package com.android.supremehappy.menutest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		//xml 내용 불러오기 : inflate - inflater 객체
		getMenuInflater().inflate(R.menu.option_menu, menu);
		//menu.add(0,10,6,"test"); (그룹번호, 아이템 id, 아이템 순서, 아이템제목)
		menu.add(0,6,6,"test");
		menu.add(0,7,7,"멍멍멍");
		
		SubMenu etc = menu.addSubMenu("기타");
		etc.add(0,8,1,"기타1");
		etc.add(0,9,2,"기타2");
		return super.onCreateOptionsMenu(menu);		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId()){
		case R.id.home: 
			return true;
		case R.id.exit: 
			finish(); 
			return true;
		case R.id.digital:
			Intent itt1= new Intent(MainActivity.this,DigitalActivity.class);
			startActivity(itt1);
			break;
		case R.id.analog:
			Intent itt2= new Intent(MainActivity.this,AnalogActivity.class);
			startActivity(itt2);
			break;
		case R.id.chrono:
			Intent itt3= new Intent(MainActivity.this,ChronoActivity.class);
			startActivity(itt3);
			break;
		case 6 :
			Toast.makeText(this, "testtesttesttesttest", Toast.LENGTH_LONG).show();
			break;
		case 7 :
			Toast.makeText(this, "멍멍멍멍멍멍멍멍멍멍왈멍멍", Toast.LENGTH_LONG).show();
			break;
		
		}
		
		return super.onOptionsItemSelected(item);
	}
}
