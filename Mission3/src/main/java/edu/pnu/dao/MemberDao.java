package edu.pnu.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import edu.pnu.domain.MemberDTO;

public class MemberDao extends JDBConnect{
	
	public MemberDao(Integer id, String pass, String name, Date regidate) {
		super(id, pass, name, regidate);
	}
	public MemberDTO getMemberDTO(Integer uid, String upass) {
		MemberDTO dto = new MemberDTO();
		String query = "SELECT * FROM member WHERE id=? AND pass=?";
		
		try {
			PreparedStatement psmt = getCon().prepareStatement(query);
			psmt.setInt(1,uid); 
			psmt.setString(2,upass); 
			ResultSet rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setId(rs.getInt("id"));
				dto.setPass(rs.getString("pass"));
				dto.setName(rs.getString(3));
				dto.setRegidate(rs.getDate(4));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
}
