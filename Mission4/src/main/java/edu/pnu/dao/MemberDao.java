package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberDTO;

public class MemberDao{
	
	private Connection con = null;
	public MemberDao() throws SQLException {
		String JDBC_URL = "jdbc:mysql://localhost:3306/new_data";
	    String USERNAME = "scott";
	    String PASSWORD = "tiger";
		
		con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}
	
	public Map<String, Object> getAllMember() throws SQLException {
	    List<MemberDTO> members = new ArrayList<>();
	    
	    String sql = "SELECT * FROM member order by id";
	    
	    boolean success = false;
	    Statement st = con.createStatement(); // Connection 객체(con)에서 생성
	    ResultSet rs = st.executeQuery(sql); // 쿼리 실행

	    while (rs.next()) { // 결과를 순회하며 한 행의 내용을 저장함
	        MemberDTO dto = new MemberDTO();
	        dto.setId(rs.getInt("id"));
	        dto.setPass(rs.getString("pass"));
	        dto.setName(rs.getString("name"));
	        dto.setRegidate(rs.getDate("regidate"));
	        members.add(dto); // 리스트에 추가
	        success = true;
	    }
	    rs.close();
	    st.close();
	    
	    Map<String, Object> map = new HashMap<>();
	    map.put("result", members);
	    map.put("sqlstring", sql);
	    map.put("success",  success);
	    
	    return map;
	}

	public MemberDTO getMemberById(Integer id) throws SQLException {
	    Statement st = con.createStatement(); // Connection 객체(con)에서 생성
	    ResultSet rs = st.executeQuery("SELECT * FROM member WHERE id="+id); // 쿼리 실행
	    MemberDTO dto = new MemberDTO();
	    
	    if (rs.next()) { // 결과를 순회하며 한 행의 내용을 저장함
	        dto.setId(rs.getInt("id"));
	        dto.setPass(rs.getString("pass"));
	        dto.setName(rs.getString("name"));
	        dto.setRegidate(rs.getDate("regidate"));
	    }
	    rs.close();
	    st.close();
	    return dto;
	}

	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		try {
			 PreparedStatement psmt = con.prepareStatement("INSERT INTO member (id, pass, name) VALUES (?, ?, ?)");
	    	 psmt.setInt(1, memberDTO.getId());
	         psmt.setString(2, memberDTO.getPass());
	         psmt.setString(3, memberDTO.getName());
			 psmt.executeUpdate();
			 psmt.close();
	    } catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
	    }
	    return getMemberById(memberDTO.getId());
		}

	public MemberDTO updateMember(MemberDTO memberDTO) throws SQLException {
		try {
			PreparedStatement psmt = con.prepareStatement("UPDATE member SET "
	                + "pass = ?, name = ?"
	                + " WHERE id = ?");
			psmt.setString(1, memberDTO.getPass());
	        psmt.setString(2, memberDTO.getName());
	        psmt.setInt(3, memberDTO.getId());
			psmt.executeUpdate();
			psmt.close();
	    } catch (Exception e) {
			System.out.println("게시물 업데이트 중 예외 발생");
			e.printStackTrace();
	    }
	    return getMemberById(memberDTO.getId());
		}

	public MemberDTO removeMember(MemberDTO memberDTO) throws SQLException {
		try {
			PreparedStatement psmt = con.prepareStatement("DELETE FROM member WHERE id = ?");
	        psmt.setInt(1, memberDTO.getId());
			psmt.executeUpdate();
			psmt.close();
	    } catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
	    }
	    return getMemberById(memberDTO.getId());
	}
}
