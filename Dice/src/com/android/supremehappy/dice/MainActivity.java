package com.android.supremehappy.dice;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	int[] player = {R.drawable.d01,R.drawable.d02,R.drawable.d03,R.drawable.d04,R.drawable.d05,R.drawable.d06};
	int[] com = {R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6};
	Button start, exit;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		start = (Button)findViewById(R.id.btn_start);
		start.setOnClickListener(new OnClickListener(){
			
			Random rd = new Random();
			int d1 = rd.nextInt(6),d2=rd.nextInt(6);
			TextView result;
			ImageView pc, npc;
			
			@Override
			public void onClick(View v) {
				
				switch(d1){
				case 1:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				case 2:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				case 3:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				case 4:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				case 5:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				case 6:
					pc=(ImageView)findViewById(R.id.imageView1);
					pc.setImageResource(player[d1]);
					break;
				}
				
				switch(d2){
				case 1:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				case 2:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				case 3:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				case 4:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				case 5:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				case 6:
					npc=(ImageView)findViewById(R.id.imageView2);
					npc.setImageResource(com[d2]);
					break;
				}
				
				int n1 = d1+1, n2=d2+1;
				result = (TextView) findViewById(R.id.textView1);
				
				if(d1>d2){
					result.setText("pc 승");
				}else if(d1==d2){
					result.setText("무승부");
				}else if(d1<d2){
					result.setText("pc 패");
				}
			}
			
		});
		
		exit = (Button)findViewById(R.id.btn_exit);
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				finish();
			}
		});
		
	}
}

