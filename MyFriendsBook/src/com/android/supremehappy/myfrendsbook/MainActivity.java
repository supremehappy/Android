package com.android.supremehappy.myfrendsbook;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public static final String DB_Name="myDB.db";
	public static final String DB_TABLE="myFriends";
	public static final String DB_CREATE=
			"create table "+DB_TABLE+
			" (_id integer primary key autoincrement, "
			+ " name text not null,"
			+ " phone text not null,"
			+ " address text not null);";
	public static final String DB_INSERT="insert into "+DB_TABLE+" (name,phone,address) values ";
	public static final String DB_DELETE="delete from "+DB_TABLE;
	public static final String DB_UPDATE="update "+DB_TABLE;
	
	public static SQLiteDatabase myDB;
	
	private EditText edit_num,edit_name, edit_phone, edit_addr;
	private Button btn_insert, btn_delete, btn_update, btn_select, btn_clear;

	private void openDatabase(){
		myDB = this.openOrCreateDatabase(DB_Name, Context.MODE_PRIVATE, null); // 해당 db 이름이 있으면 열기, 없으면 생성
		
		if(myDB!=null){
			Toast.makeText(this, "DB 사용 가능", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(this, "DB 사용 불가능", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void createTable(){
		try{
			myDB.execSQL(DB_CREATE);
			Toast.makeText(this, "테이블 생성", Toast.LENGTH_SHORT).show();
		}catch(Exception e){
			Toast.makeText(this, "테이블 생성 불가", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean insertTable(){
		String sql = DB_INSERT;
		
		String name =edit_name.getText().toString();
		if(name.equals("")){
			Toast.makeText(this, "이름 입력", Toast.LENGTH_LONG).show();
			return false;
		}
		
		String phone = edit_phone.getText().toString();
		if(phone.equals("")){
			Toast.makeText(this, "전화번호 입력", Toast.LENGTH_LONG).show();
			return false;
		}
		
		String addr = edit_addr.getText().toString();
		if(addr.equals("")){
			Toast.makeText(this, "주소 입력", Toast.LENGTH_LONG).show();
			return false;
		}
		
		sql = sql+"('"+name+"','"+phone+"','"+addr+"');";
		
		try{
			myDB.execSQL(sql);
			Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
			return true;
		}catch(Exception e){
			Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	private void selectTable(){// 무조건 조회
		String sql = "select * from "+DB_TABLE+";";
		Cursor c = myDB.rawQuery(sql, null);//조회 실행
		
		startManagingCursor(c);// 커서 객체 자동 관리
		
		String[] from = {"_id","name","phone","address"};
		int[] to={R.id._id,R.id.txt_name,R.id.txt_phone,R.id.txt_addr};//출력될 위젯(xml 파일)
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.myfriends,c,from,to);
		
		setListAdapter(adapter);
	}
	
	private boolean deleteTable(){
		String sql = DB_DELETE;
		
		sql = sql + " where 1=1 ";
		
		String num =edit_num.getText().toString();
		sql = sql + "and _id = "+num;
		
		String name =edit_name.getText().toString();
		if(name!=null){
			sql = sql + " and name='"+name+"' ";
		}
		
		String phone = edit_phone.getText().toString();
		if(phone!=null){
			sql = sql + " and phone='" + phone+"' ";
		}
		
		String addr = edit_addr.getText().toString();
		if(addr!=null){
			sql = sql + " and address='" + addr+"' ";
		}
		
		sql = sql+";";
		
		try{
			myDB.execSQL(sql);
			Toast.makeText(this, "삭제 성공", Toast.LENGTH_SHORT).show();
			return true;
		}catch(Exception e){
			Toast.makeText(this, "삭제 실패", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	private boolean updateTable(){
		String sql = DB_UPDATE;
		
		String num =edit_num.getText().toString();
		String name =edit_name.getText().toString();
		String phone = edit_phone.getText().toString();
		String addr = edit_addr.getText().toString();
		
		sql = sql + " set name='"+name+"', phone='"+
				phone+"', address='"+addr+"' where _id="+num+";";
		
		try{
			myDB.execSQL(sql);
			Toast.makeText(this, "변경 성공", Toast.LENGTH_SHORT).show();
			return true;
		}catch(Exception e){
			Toast.makeText(this, "변경 실패", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		openDatabase();
		createTable();
		
		edit_num=(EditText)findViewById(R.id.edit_num);
		edit_num.setEnabled(false);
		
		edit_name=(EditText)findViewById(R.id.edit_name);
		edit_phone=(EditText)findViewById(R.id.edit_phone);
		edit_addr=(EditText)findViewById(R.id.edit_addr);
		
		btn_insert= (Button)findViewById(R.id.btn_insert);
		btn_insert.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// 데이터 삽입을 위한 다이얼로그 생성
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				
				//setPositiveButton(버튼 문자열, 리스너) || setNegativeButton(버튼 문자열, 리스너)  
				builder.setMessage("정말 삽입?").setCancelable(false)
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						if(insertTable()){
							selectTable();
						}
						
						
					}
				})
				.setNegativeButton("no", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						
					}
				});
				
				
				AlertDialog dialog = builder.create(); // 다이얼로그 생성 | 다이얼로그를 통한 버튼 생성은 최대 3개
				
				dialog.show();
				
			}
			
		});

		btn_delete= (Button)findViewById(R.id.btn_delete);
		btn_delete.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				
				builder.setMessage("정말 지움?").setCancelable(false)
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						deleteTable();
						selectTable();
					}
				})
				.setNegativeButton("no", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						
					}
				});
				
				AlertDialog dialog = builder.create(); // 다이얼로그 생성 | 다이얼로그를 통한 버튼 생성은 최대 3개
				
				dialog.show();
				
			}
			
		});
		
		btn_update= (Button)findViewById(R.id.btn_update);
		btn_update.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				
				builder.setMessage("정말 수정?").setCancelable(false)
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						updateTable();
						selectTable();
						
					}
				})
				.setNegativeButton("no", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						
					}
				});
				
				AlertDialog dialog = builder.create(); // 다이얼로그 생성 | 다이얼로그를 통한 버튼 생성은 최대 3개
				
				dialog.show();
				
			}
			
		});
		
		btn_select= (Button)findViewById(R.id.btn_select);
		btn_select.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				String num =edit_num.getText().toString();
				String name =edit_name.getText().toString();
				String phone = edit_phone.getText().toString();
				String addr = edit_addr.getText().toString();
				
				if(num.equals("")&&name.equals("")&&phone.equals("")&&addr.equals("")){//무조건 조회
					selectTable();
				}else{//조건 조회
					String sql  = "select * from myfriends where 1=1";
					
					if(! name.equals("")){ // 이름이 입력된 경우
						sql = sql + " and name='"+name+"'";
					}
					if(! phone.equals("")){ 
						sql = sql + " and phone='"+phone+"'";
					}
					if(! addr.equals("")){ 
						sql = sql + " and address='"+addr+"'";
					}
					sql=sql+";";
					
					try{
						Cursor c = myDB.rawQuery(sql, null);
						startManagingCursor(c);
						String[] from = {"_id","name","phone","address"};
						int[] to={R.id._id,R.id.txt_name,R.id.txt_phone,R.id.txt_addr};//출력될 위젯(xml 파일)
						ListAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.myfriends,c,from,to);
						
						setListAdapter(adapter);
					}catch(Exception e){
						Toast.makeText(MainActivity.this, "조회 실패", Toast.LENGTH_SHORT).show();
					}
				}
				
			}
			
		});
		
		btn_clear= (Button)findViewById(R.id.btn_clear);
		btn_clear.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {

				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				
				builder.setMessage("정말 지움?").setCancelable(false)
				.setPositiveButton("yes", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						edit_num.setText("");
						edit_name.setText("");
						edit_phone.setText("");
						edit_addr.setText("");
						
					}
				})
				.setNegativeButton("no", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						dialog.cancel();
						
					}
				});
				
				AlertDialog dialog = builder.create(); // 다이얼로그 생성 | 다이얼로그를 통한 버튼 생성은 최대 3개
				
				dialog.show();
			}
		});
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		LinearLayout container = (LinearLayout)v;
		TextView tv1 = (TextView)container.findViewById(R.id._id);
		TextView tv2 = (TextView)container.findViewById(R.id.txt_name);
		TextView tv3 = (TextView)container.findViewById(R.id.txt_phone);
		TextView tv4 = (TextView)container.findViewById(R.id.txt_addr);
		
		edit_num.setText(tv1.getText());
		edit_name.setText(tv2.getText());
		edit_phone.setText(tv3.getText());
		edit_addr.setText(tv4.getText());
		
	}
}


