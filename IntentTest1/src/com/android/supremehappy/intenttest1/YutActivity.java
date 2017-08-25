package com.android.supremehappy.intenttest1;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class YutActivity extends Activity {

    String[] mal = {"모","도","개","걸","윷"};
    int[] imgYut={R.drawable.yut_1, R.drawable.yut_0};
    Button btn_start, btn_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yut_activity);

        btn_start=(Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener(){
           
        	ImageView iv1, iv2, iv3, iv4; TextView result;
        	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Random rnd = new Random();
				int n1 = 1- rnd.nextInt(10)/6;
				int n2 = 1- rnd.nextInt(10)/6;
				int n3 = 1- rnd.nextInt(10)/6;
				int n4 = 1- rnd.nextInt(10)/6;
				
				iv1 = (ImageView) findViewById(R.id.img_yut1);
				iv1.setImageResource(imgYut[n1]);
				
				iv2 = (ImageView) findViewById(R.id.img_yut2);
				iv2.setImageResource(imgYut[n2]);
				
				iv3 = (ImageView) findViewById(R.id.img_yut3);
				iv3.setImageResource(imgYut[n3]);
				
				iv4 = (ImageView) findViewById(R.id.img_yut4);
				iv4.setImageResource(imgYut[n4]);
				
				result=(TextView) findViewById(R.id.txt_result);
				
				int n = n1+n2+n3+n4;
				
				result.setText(mal[n]);
			}
        });

        btn_end=(Button) findViewById(R.id.btn_exit);
        btn_end.setOnClickListener(new View.OnClickListener(){
          
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        });
    }
}