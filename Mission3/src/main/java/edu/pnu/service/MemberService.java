package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
    private MemberDao memberDao;
    

    public MemberService() {
        this.memberDao = new MemberDao();
    }

    public List<MemberDTO> getAllMember() {
        return memberDao.getAllMember();
    }
    
    public MemberDTO getMemberById(integer id) {
    	for (MemberDTO m : list) {
    		
    	}
    }
}
