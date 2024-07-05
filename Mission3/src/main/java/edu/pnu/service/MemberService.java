package edu.pnu.service;

import java.util.List;

import edu.pnu.dao.MemberDao;
import edu.pnu.domain.MemberDTO;

public class MemberService {
    private MemberDao memberDao;

    public MemberService() {
        this.memberDao = new MemberDao();
    }

    public List<MemberDTO> getAllMembers() {
        return memberDao.getAllMembers();
    }
}
