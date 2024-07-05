package edu.pnu.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@RestController
public class MemberController {

	private List<MemberVO> list = new ArrayList<>();

	public MemberController(List<MemberVO> list) {
        this.list = list;
    }
	
	public MemberController() {
		for (int i=1; i<=10; i++){
			list.add(MemberVO.builder()
					.id(i)
					.name("name"+i)
					.pass("pass"+i)
					.regidate(new Date())
					.build());
		}
	}
	
	
   
}
