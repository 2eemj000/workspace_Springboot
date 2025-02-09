package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController // @Controller + @Responsebody 합친 의미
public class MemberController {
	
	private List<MemberVO> list = new ArrayList<>();
	
	public MemberController() {
		for (int i=1; i<=10; i++){
			list.add(MemberVO.builder() // build 패턴으로 만듦 (MemberVO에서 @builder)
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date())
					.build()); // 마지막에 이렇게 닫아줌
		}
	}
	
	// 검색(Read - select)
	@GetMapping("/members")
	public List<MemberVO> getAllMember() {
	return list;
	}

	
	// 검색(Read - select) # Params에 id를 넣어서 실행
	@GetMapping("/member")
	public MemberVO getMemberById(Integer id) {
		for (MemberVO m : list) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	
	// 입력(Create - insert) # Body > form-data에 key(id, pass,name) 넣어서 실행 (! post, put, del도 마찬가지 !)
	@PostMapping("/member")
	public MemberVO addMember(MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId()+"가 이미 존재합니다.");
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}
	
	// 입력(Create - insert) # Body > raw > JSON 후 {"id": --, "pass": --, "name":--}
	@PostMapping("/memberJSON")
	public MemberVO addMemberJSON(@RequestBody MemberVO memberVO) {
		if (getMemberById(memberVO.getId()) != null) {
			System.out.println(memberVO.getId() + "가 이미 존재합니다.");
			return null;
		}
		memberVO.setRegidate(new Date());
		list.add(memberVO);
		return memberVO;
	}

	// 수정(Update - update)
	@PutMapping("/member")
	public int updateMember(MemberVO memberVO) {
		MemberVO m = getMemberById(memberVO.getId());
		if (m==null) 
			return 0;
		m.setName(memberVO.getName());
		m.setPass(memberVO.getPass());
		return 1;
		}
	
	// 삭제 (Delete - delete)
	@DeleteMapping("/member")
	public int removeMember(Integer id) {
		try {
			list.remove(getMemberById(id));
			} catch (Exception e) {
				return 0;
			}
			return 1;
		}
	}
