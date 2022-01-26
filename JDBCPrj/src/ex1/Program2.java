package ex1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Program2 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String title="Test2";
		String writerId="lec";
		String content="hahaha";
		String file="";
		
		String url="jdbc:oracle:thin:@localhost:1521/xepdb1";
		String sql = "INSERT INTO notice ("
				+ "    title, "
				+ "    writer_id, "
				+ "    content, "
				+ "    files "
				+ ") VALUES (?,?,?,?)";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(url,"NEWLEC","12345");
		// Statement st = con.createStatement();
		
		// SELECT �� �ƴϰ� ����, ����, ������ ������ ����ϴ� ���, ��ȯ�ϴ� ����� ����
		// ������ �Ʒ��� ���� PreparedStatement �ڵ� & 
		// st.excuteUpdate() �� �����
		PreparedStatement st= con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		st.setString(4, file);
		
		int result=st.executeUpdate();
		
		System.out.println(result);
		
		

	
		
		
		
		st.close();
		con.close();
	}

}
