package edu.pnu.service;

import java.sql.SQLException;
import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
    private final MemberDao memberDao;

    public MemberService() throws SQLException {
        this.memberDao = new MemberDao();
    }

    public List<MemberDTO> getAllMember() throws SQLException {
        return memberDao.getAllMember();
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
