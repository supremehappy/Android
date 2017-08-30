package com.android.supremehappy.s2unitd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public static final String ROOT_DIR="/data/data/com.android.supremehappy.s2unitd/databases/";
	public static String TABLE_NAME="units";
	public static final String SELECT_TABLE="select * from "+TABLE_NAME+";";
	
	public SQLiteDatabase db ;
	public Cursor cursor;
	public DBHelper mHelper;
	
	private Button btn_search;
	private EditText edit_unit;
	
	public void selectTable(){
		String sql = SELECT_TABLE;
		Cursor c = db.rawQuery(sql, null);//조회 실행
		
		startManagingCursor(c);// 커서 객체 자동 관리
		
		String[] from = {"_id","name","price","supply","gauge","defense","offense"};
		int[] to={R.id._id,R.id.name,R.id.price,R.id.supply,R.id.gauge,R.id.defense,R.id.offense};//출력될 위젯(xml 파일)
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.uints,c,from,to);
		
		setListAdapter(adapter);
	}
	
	public void setDB(){
		File folder = new File(ROOT_DIR);
		
		if(!folder.exists()){
			folder.mkdirs(); // 해당 폴더 생성
		}
		
		AssetManager assetManager = getResources().getAssets(); // Asset 폴더 관리 객체 
		File curFile = new File(ROOT_DIR+"unit3.db");
		InputStream is = null;
		FileOutputStream fos = null;
		long filesize = 0;
		
		try{
			is=assetManager.open("unit3.db",AssetManager.ACCESS_BUFFER);
			filesize = is.available();
			
			if(curFile.length()<=0){
				byte[] tempdata = new byte[(int) filesize];
				
				is.read(tempdata);
				is.close();
				curFile.createNewFile();
				
				fos = new FileOutputStream(curFile);
				
				fos.write(tempdata);
				fos.close();
			}
		}catch(Exception e){
			
		}
	}
	
	class DBHelper extends SQLiteOpenHelper{

		public DBHelper(Context context){
			super(context,"unit3.db",null,1);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		setDB();
		Toast.makeText(this, "setDB", Toast.LENGTH_SHORT).show();
		mHelper = new DBHelper(this);
		Toast.makeText(this, "mHelper", Toast.LENGTH_SHORT).show();
		db = mHelper.getWritableDatabase();
		Toast.makeText(this, "DB 가져옴", Toast.LENGTH_SHORT).show();
		edit_unit = (EditText) findViewById(R.id.edit_unit);
		
		btn_search = (Button) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				String str = edit_unit.getText().toString();
				
				if(str.equals("")){
					selectTable();
				}else{
					String sql = "select * from "+TABLE_NAME+" where 1=1";
					
					if(! str.equals("")){
						sql = sql + " and name='"+str+"'";
					}
					
					sql = sql +";";
					
					try{
						Cursor c = db.rawQuery(sql, null);//조회 실행
						
						startManagingCursor(c);// 커서 객체 자동 관리
						
						String[] from = {"_id","name","price","supply","gauge","defense","offense"};
						int[] to={R.id._id,R.id.name,R.id.price,R.id.supply,R.id.gauge,R.id.defense,R.id.offense};//출력될 위젯(xml 파일)
						ListAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.uints,c,from,to);
						
						setListAdapter(adapter);
					}catch(Exception e){
						Toast.makeText(MainActivity.this, "조회 실패", Toast.LENGTH_SHORT).show();
					}
				}
				
			}
			
		});
		
	}
	
	
}