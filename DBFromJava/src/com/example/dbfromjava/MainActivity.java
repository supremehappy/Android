package com.example.dbfromjava;

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
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public static final String ROOT_DIR="/data/data/com.example.dbfromjava/databases/";
	public static String TABLE_NAME="myGroups";
	public static final String SELECT_TABLE="select * from "+TABLE_NAME+";";
	
	public SQLiteDatabase db ;
	public Cursor cursor;
	public DBHelper mHelper;
	
	public void selectTable(){
		String sql = SELECT_TABLE;
		Cursor c = db.rawQuery(sql, null);//조회 실행
		
		startManagingCursor(c);// 커서 객체 자동 관리
		
		String[] from = {"_id","grp_name","name","phone","address"};
		int[] to={R.id._id,R.id.txt_grp,R.id.txt_name,R.id.txt_phone,R.id.txt_addr};//출력될 위젯(xml 파일)
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.my_group,c,from,to);
		
		setListAdapter(adapter);
	}
	
	public void setDB(){
		File folder = new File(ROOT_DIR);
		
		if(!folder.exists()){
			folder.mkdirs(); // 해당 폴더 생성
		}
		
		AssetManager assetManager = getResources().getAssets(); // Asset 폴더 관리 객체 
		File curFile = new File(ROOT_DIR+"myData.db");
		InputStream is = null;
		FileOutputStream fos = null;
		long filesize = 0;
		
		try{
			is=assetManager.open("myData.db",AssetManager.ACCESS_BUFFER);
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
			super(context,"myData.db",null,1);
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
		
		mHelper = new DBHelper(this);
		
		db = mHelper.getWritableDatabase();
		selectTable();
	}
}
