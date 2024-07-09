// [ DI 방법 ] : DAO -> SERVICE(@Service) -> CONTROLLER(@RestController)
// 1. 필드에 Autowired를 적용하는 방법
//  @Autowired
//  private MemberService memberService;
// 2. 생성자를 이용하는 방법
//  @ Autowired
//  public MemberController(MemberService memberService) 
// 3. Setter를 이용한 방법
//  @ Autowired
//  public void setMemberService(MemberService memberService) 
// 4. Lombok Annotation을 이용한 방법
//  @RequiredArgsConstructor
//  필드 앞에 final 예약어가 필요

package edu.pnu.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberDTO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {
	@ Autowired
	private MemberService memberService;
	
//	public MemberController() throws SQLException {
//		memberService = new MemberService();
//	}
	
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
