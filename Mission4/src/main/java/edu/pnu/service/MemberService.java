package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import edu.pnu.dao.LogDao;
import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
    private final MemberDao memberDao;
    private final LogDao logDao;

    public MemberService() throws SQLException {
        this.memberDao = new MemberDao();
        this.logDao = new LogDao();
    }

    public List<MemberDTO> getAllMember() throws SQLException {
    	
    	// 처음에는 map 돌면서 타입지정안하고 object로 다받음
    	Map<String, Object> map = memberDao.getAllMember();
    	
    	@SuppressWarnings("unchecked")
    	// 이후에 각각 type casting해주기
		List<MemberDTO> list = (List<MemberDTO>)map.get("result");
    	String sqlString = (String)map.get("sqlstring");
    	boolean success = (boolean)map.get("success");
    	
    	// 첫번째 인자는 method라서 여기서는 "GET"으로
    	logDao.addLog("GET", sqlString, success);

    	return list;
    }

	public MemberDTO getMemberById(Integer id) throws SQLException {
		return memberDao.getMemberById(id);
	}

	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		return memberDao.addMember(memberDTO);
	}

	public MemberDTO updateMember(MemberDTO memberDTO) throws SQLException {
		return memberDao.updateMember(memberDTO);
	}

	public MemberDTO removeMember(MemberDTO memberDTO) throws SQLException {
		return memberDao.removeMember(memberDTO);
	}
	
	
}





