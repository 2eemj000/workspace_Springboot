package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;

@Controller
public class LoginController {
	@GetMapping("/login")
	public void login() {
		System.out.println("login 요청");
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		System.out.println("loginSuccess 요청");
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
	System.out.println("accessDenied");
	}
	
	// ---------- (추가) ---------- //
	
	// 로그인 세션 정보 확인용 URL
	@GetMapping("/auth")
	public @ResponseBody ResponseEntity<?> auth(@AuthenticationPrincipal User user) {
	if (user == null) {
	return ResponseEntity.ok("로그인 상태가 아닙니다.");
	}
	return ResponseEntity.ok(user);
	}
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/join") // 회원 가입 폼을 렌더링
	public String join() {
		return "join"; // join.html로 이동
	}
	
	@PostMapping("/join") // 회원 가입 후 welcome 페이지로 리다이렉트
	public String joinProc(Member member) {
		memberService.save(member);
		return "welcome";
	}
}
