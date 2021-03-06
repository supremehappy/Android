package com.android.supremehappy.eventtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	TextView tv, tv1, tv2;
	RadioGroup rg;
	ToggleButton tg;
	Button btn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//체크박스 이벤트 처리 (체크박스에서 발생하는 이벤트 = OnClick() / 리스너 = OnClickListener)
		final CheckBox ch = (CheckBox) findViewById(R.id.checkBox1); // xml 에 설게된 위젯 불러오는 메서드 findViewById();
		tv = (TextView) findViewById(R.id.check_result);
		tv1 = (TextView) findViewById(R.id.textView1);
		tv2 = (TextView) findViewById(R.id.textView2);
		rg = (RadioGroup) findViewById(R.id.radioGroup1);
		tg = (ToggleButton) findViewById(R.id.toggleButton1);
		btn = (Button) findViewById(R.id.btn_exit);
		
		//체크박스
		ch.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				if(ch.isChecked()){
					tv.setText("checked");
				}else{
					tv.setText("not checked");
				}
				
			}
			
		});
		
		//라디오 그룹
		rg.setOnCheckedChangeListener(new OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton)findViewById(checkedId);
				CharSequence title = rb.getText(); // 버튼 제목 추출
				
				tv1.setText(title);
				
			}
			
		});
		
		Log.d("first", "out");
		Log.i("first", "out");
		Log.e("first", "out");
		Log.v("first", "out");
		Log.w("first", "out");
		
		//토글버튼
		tg.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if(tg.isChecked()){
					tv2.setText("selected");
				}else{
					tv2.setText("not selected");
				}
				
			}
			
		});
		
		Log.d("event", "out");
		Log.i("event", "out");
		Log.e("event", "out");
		Log.v("event", "out");
		Log.w("event", "out");
		
		//버튼
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				finish();
			}
			
		});
		
	}
}


