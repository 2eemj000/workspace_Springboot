package edu.pnu.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	private MemberService memberService;
	
	public MemberController() throws SQLException {
		memberService = new MemberService();
	}
	
	// 검색(Read - select)
	@GetMapping("/members")
	public List<MemberDTO> getAllMember() throws SQLException {
		return memberService.getAllMember();
	}

	// 검색(Read - select)
	@GetMapping("/member")
	public MemberDTO getMemberById(Integer id) throws SQLException {
		return memberService.getMemberById(id);
	}

	// 입력(Create - insert)
	@PostMapping("/member")
	public MemberDTO addMember(MemberDTO memberDTO) throws SQLException {
		return memberService.addMember(memberDTO);
	}
	
	// 수정(Update - update)
	@PutMapping("/member")
	public MemberDTO updateMember(MemberDTO memberDTO) throws SQLException {
		return memberService.updateMember(memberDTO);
	}
	
	// 삭제(Delete - delete)
	@DeleteMapping("/member")
	public MemberDTO removeMember(MemberDTO memberDTO) throws SQLException {
		return memberService.removeMember(memberDTO);
	}
}
