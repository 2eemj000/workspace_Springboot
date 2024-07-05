package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.pnu.domain.MemberDTO;

public class MemberDao{
	
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/new_data";
    private static final String USERNAME = "scott";
    private static final String PASSWORD = "tiger";
	
	public MemberDTO getMemberDTO(Integer uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
		
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            psmt = conn.prepareStatement(query);
            psmt.setInt(1, uid);
            psmt.setString(2, upass);
            
            rs = psmt.executeQuery();
            
            if (rs.next()) {
                dto.setId(rs.getInt("id"));
                dto.setPass(rs.getString("pass"));
                dto.setName(rs.getString("name"));
                dto.setRegidate(rs.getDate("regidate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (psmt != null) psmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return dto;
	    }
}
