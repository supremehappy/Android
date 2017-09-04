package com.android.supremehappy.timedatetest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

public class MainActivity extends Activity {

	TextView result1, result2;
	TimePicker tp;
	DatePicker dp;
	CheckBox cb;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		result1 = (TextView) findViewById(R.id.result1);
		result2 = (TextView) findViewById(R.id.result2);
		
		tp = (TimePicker) findViewById(R.id.timePicker1);
		tp.setOnTimeChangedListener(new OnTimeChangedListener(){

			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				
				String msg = "";
				
				msg = msg +hourOfDay+ "시, "+minute+"분";
				result2.setText(msg);
				
				// 선택한 시간으로 설정
				tp.setCurrentHour(hourOfDay); 
				tp.setCurrentMinute(minute); 
				
			}
			
		});
		
		dp = (DatePicker) findViewById(R.id.datePicker1);
		dp.init(dp.getYear(), dp.getMonth(), dp.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

				String msg = "";
				monthOfYear = monthOfYear+1;
				msg = msg +year+ "년,"+monthOfYear+"월,"+dayOfMonth+"일";
				result1.setText(msg);
				
			}
		});
		
		cb = (CheckBox) findViewById(R.id.checkBox1);
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

				if(cb.isChecked()){
					tp.setEnabled(isChecked); // 타임피커 활성화
					tp.setVisibility(View.VISIBLE);
				}else{
					tp.setVisibility(View.INVISIBLE);
				}
				
			}
			
		});
	}
}
