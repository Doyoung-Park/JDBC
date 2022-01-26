package com.newlecture.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.app.entity.Notice;

public class NoticeService {
	private String url="jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String uid="NEWLEC";
	private String pwd="12345";
	private String driver="oracle.jdbc.driver.OracleDriver";
	
	public List<Notice> getList(int page) throws ClassNotFoundException, SQLException{
		
		int start= 1+(page-1)*10; 	// 등차수열 일반항
		int end= 10*page;
		
		String sql = "SELECT * FROM NOTICE_VIEW WHERE NUM BETWEEN ? AND ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		// 출력의 결과가 테이블처럼 있는 SELECT 쿼리를 사용할 때는
		// 아래와 같은 코드를 사용함
		// con.createStatement() & st.executeQuery()
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, start);
		st.setInt(2, end);
		
		ResultSet rs = st.executeQuery(); 
		
		
		List<Notice> list = new ArrayList<Notice>();
				
		while(rs.next()){
			int id=rs.getInt("id");
			String title= rs.getString("title");
			String writerId=rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			String files= rs.getString("FILES");
			
			Notice notice=new Notice(
					id,
					title,
					writerId,
					regDate,
					content,
					hit,
					files);
			
			list.add(notice);
			
		}
		
		
		rs.close();
		st.close();
		con.close();
		
		return list;
	}
	
	public int insert(Notice notice) throws ClassNotFoundException, SQLException {
		
		String title=notice.getTitle();
		String writerId=notice.getWriterId();
		String content=notice.getContent();
		String file=notice.getFiles();
		
		
		String sql = "INSERT INTO notice ("
				+ "    title, "
				+ "    writer_id, "
				+ "    content, "
				+ "    files "
				+ ") VALUES (?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		// Statement st = con.createStatement();
		
		// SELECT 가 아니고 삽입, 수정, 삭제의 쿼리를 사용하는 경우, 반환하는 결과가 없기
		// 때문에 아래와 같은 PreparedStatement 코드 & 
		// st.excuteUpdate() 를 사용함
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, file);
		
		int result=st.executeUpdate();
	
		
		st.close();
		con.close();
		
		return result;
	}
	public int update(Notice notice) throws ClassNotFoundException, SQLException {

		String title=notice.getTitle();
		String content=notice.getContent();
		String files=notice.getFiles();
		int id=notice.getId();
		
		String sql = "UPDATE NOTICE"
				+ " SET"
				+ "	TITLE=?, "
				+ "	CONTENT=?, "
				+ "	FILES=? "
				+ "WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		// Statement st = con.createStatement();
		
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, content);
		st.setString(3, files);
		st.setInt(4, id);
		
		int result=st.executeUpdate();
	
		st.close();
		con.close();
		
		return result;
	}
	public int delete(int id) throws ClassNotFoundException, SQLException {

		String sql = "DELETE NOTICE WHERE ID=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		
		// Statement st = con.createStatement();
		
		PreparedStatement st= con.prepareStatement(sql);
		st.setInt(1, id);
		
		int result=st.executeUpdate();
		
		
		st.close();
		con.close();
		
		return result;
	}
}
