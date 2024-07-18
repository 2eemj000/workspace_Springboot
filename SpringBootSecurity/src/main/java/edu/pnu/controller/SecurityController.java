package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	// [컨트롤러 메소드 리턴에 따른 View 호출]
	// String이면 (return).html을 호출하라는 의미
	// void면 url명.html을 호출하라는 의미
	
	@GetMapping({"/", "/index"})
	public String index() {
	System.out.println("index 요청");
	return "index";
	}
	@GetMapping("/member")
	public void member() {
	System.out.println("Member 요청");
	}
	@GetMapping("/manager")
	public void manager() {
	System.out.println("Manager 요청");
	}
	@GetMapping("/admin")
	public void admin() {
		System.out.println("Admin요청");
	}
//	@GetMapping("/loginSuccess")
//	public void loginSuccess() {
//		System.out.println("loginSuccess 요청");
//	}
}
