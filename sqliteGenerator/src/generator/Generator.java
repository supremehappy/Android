package generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Generator {

	public static void main(String[] args) {
		MakeSqliteFile msf = new MakeSqliteFile();
		msf.makeDBFile();
		msf.createTable();
		msf.insertData();

	}

}
class MakeSqliteFile{
	Connection conn;
	Statement stmt; 
	PreparedStatement pstmt;
	
	public static final String TABLE_NAME="units";
	public static final String CREATE_TABLE="create table "+TABLE_NAME+" (_id integer primary key autoincrement, "+
			"name text not null,"+
			"price text not null,"+
			"supply text not null,"+
			"gauge text not null,"+
			"defense text not null,"+
			"offense text not null);";
	
	public static final String INSERT_TABLE="insert into "+TABLE_NAME+" (name,price,supply,gauge,defense,offense) "+
			"values(?,?,?,?,?,?);";
	
	public void makeDBFile(){
		try{
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("JDBC:sqlite:unit.db");
			
			if(conn==null){
				System.out.println("sqlite DB 생성 실패");
				return;
			}
			System.out.println("sqlite DB 생성");
			stmt = conn.createStatement();
			stmt.executeUpdate("drop table if exists "+TABLE_NAME+";");
		}catch(Exception e){
			System.out.println("sqlite DB 생성 중 문제 발생");
		}
	}
	
	public void createTable(){
		try{
			stmt.executeUpdate(CREATE_TABLE);
			System.out.println("테이블 생성");
		}catch(Exception e){
			System.out.println("테이블 망함");
		}
	}
	
	public void insertData(){
		try{
			pstmt=conn.prepareStatement(INSERT_TABLE);
			pstmt.setString(1, "탐사정");
			pstmt.setString(2, "50 / 0");
			pstmt.setString(3, "1");
			pstmt.setString(4, "20 / 20 / 0");
			pstmt.setString(5, "0");
			pstmt.setString(6, "5");
			pstmt.addBatch();//배치작업 설정
			System.out.println("탐사정 입력");
			
			pstmt=conn.prepareStatement(INSERT_TABLE);
			pstmt.setString(1, "광전사");
			pstmt.setString(2, "100 / 0");
			pstmt.setString(3, "2");
			pstmt.setString(4, "100 / 50 / 0");
			pstmt.setString(5, "1");
			pstmt.setString(6, "8*2");
			pstmt.addBatch();//배치작업 설정
			System.out.println("광전사 입력");
			
			pstmt=conn.prepareStatement(INSERT_TABLE);
			pstmt.setString(1, "파수기");
			pstmt.setString(2, "50 / 100");
			pstmt.setString(3, "2");
			pstmt.setString(4, "40 / 40 / 200");
			pstmt.setString(5, "1");
			pstmt.setString(6, "6");
			pstmt.addBatch();//배치작업 설정
			System.out.println("파수기 입력");
			
			pstmt=conn.prepareStatement(INSERT_TABLE);
			pstmt.setString(1, "추적자");
			pstmt.setString(2, "125 / 50");
			pstmt.setString(3, "2");
			pstmt.setString(4, "80 / 80 / 0");
			pstmt.setString(5, "1");
			pstmt.setString(6, "10(중14)");
			pstmt.addBatch();//배치작업 설정
			System.out.println("추적자 입력");
			
			pstmt=conn.prepareStatement(INSERT_TABLE);
			pstmt.setString(1, "사도");
			pstmt.setString(2, "100 / 25");
			pstmt.setString(3, "2");
			pstmt.setString(4, "70 / 70 / 0");
			pstmt.setString(5, "1");
			pstmt.setString(6, "10(경22)");
			pstmt.addBatch();//배치작업 설정
			System.out.println("사도 입력");
			
			pstmt.executeBatch();// 배치작업 실행
			
			System.out.println("배치작업 종료");
			
		}catch(Exception e){
			System.out.println("배치작업 문제발생");
			e.printStackTrace();
		}
	}
	
}