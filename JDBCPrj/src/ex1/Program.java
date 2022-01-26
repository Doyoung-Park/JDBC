package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url="jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "SELECT * FROM NOTICE1 where hit>10";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"NEWLEC","12345");
		
		// 출력의 결과가 테이블처럼 있는 SELECT 쿼리를 사용할 때는
		// 아래와 같은 코드를 사용함
		// con.createStatement() & st.executeQuery()
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
//	
		while(rs.next()){
			int id=rs.getInt("id");
			String title= rs.getString("title");
			String writerId=rs.getString("writer_id");
			Date regDate = rs.getDate("regdate");
			String content = rs.getString("content");
			int hit = rs.getInt("hit");
			
			
			System.out.printf("id: %d, title: %s, writerID: %s,"
					+ "regDate:%s, content: %s, hit: %d \n", id, title, writerId, regDate,
					content, hit);
		}
		
		
		rs.close();
		st.close();
		con.close();
	}

}
