package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LogDao {
	 private Connection con = null;
     public LogDao() throws SQLException {
	        String JDBC_URL = "jdbc:mysql://localhost:3306/new_data";
	        String USERNAME = "scott";
	        String PASSWORD = "tiger";
	        
	        con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	    }

	  public void addLog(String method, String sqlString, boolean success) throws SQLException {
	       try {PreparedStatement psmt = con.prepareStatement("INSERT INTO dblog (method, sqlstring, regidate, success) VALUES (?, ?, ?)");
	            psmt.setString(1, method);
	            psmt.setString(2, sqlString);
	            psmt.setBoolean(3, success);
	            psmt.executeUpdate();
	            psmt.close();
	        } catch (SQLException e) {
	            System.err.println("로그 추가 중 오류 발생: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }
}
