package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;

@RestController
public class MemberService {
		
		private MemberService memberService;
		private List<MemberVO> list;
		
		// 검색(Read - select)
		@GetMapping("/members")
		public List<MemberVO> getAllMember() {
		return list;
		}

		
		// 검색(Read - select)
		@GetMapping("/member")
		public MemberVO getMemberById(Integer id) {
			for (MemberVO m : list) {
				if (m.getId() == id)
					return m;
			}
			return null;
		}
	
		// 입력(Create - insert)
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
