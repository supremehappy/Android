package com.android.supremehappy.xmlreadingtest;

import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Button btn;
	private TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv=(TextView) findViewById(R.id.result);
		btn=(Button) findViewById(R.id.button1);
		btn.setOnClickListener(reader);
	}
	
	OnClickListener reader = new OnClickListener(){
		public void onClick(View v){
			XmlPullParser parser = getResources().getXml(R.xml.order);
			StringBuilder builder = new StringBuilder();
			
			try{
				while(parser.next()!=XmlPullParser.END_DOCUMENT){
					String name = parser.getName(); // 첫번째 항목 가져옴
					
					String position = null;
					String brand = null;
					
					if(name != null && name.equals("car")){ // element 검사
						int size = parser.getAttributeCount();// 속성 갯수 가져옴
						
						for(int i = 0; i<size ; i++){
							String attName = parser.getAttributeName(i); // 속성 이름
							String attValue = parser.getAttributeValue(i); // 속성 값
							
							if(attName !=null && (attName.equals("position"))){
								position = attValue;
							}else if(attName!=null &&(attName.equals("brand"))){
								brand=attValue;
							}
						}
						if(position != null && brand != null){
							builder.append(position+", "+ brand); // 문자열 생성
							
						}
					}
				}
				tv.setText(builder.toString());
			}catch(Exception e){
				Toast.makeText(MainActivity.this, "xml error", Toast.LENGTH_LONG).show();
			}
		}
	};
}
