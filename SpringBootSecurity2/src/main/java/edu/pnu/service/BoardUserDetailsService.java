package edu.pnu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

//인증 관리 필터가 인증을 처리하기 위해서는 
//사용자 정보가 저장된 UserDetails객체와 UserDetails객체에 실제 데이터베이스에서 검색한 사용자를 저장하는 UserDetailsService객체가 필요 
//스프링 시큐리티 관리자는 UserDetailsService객체를 통해 UserDetails객체를 획득 -> 거기서 인증과 인가에 필요한 정보들을 추출

@Service
public class BoardUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// memRepo에서 사용자 정보 검색
		Member member = memberRepo.findById(username)
								.orElseThrow(()->new UsernameNotFoundException("Not Found"));
		System.out.println(member); // 검색된 사용자정보 출력

		return new User(member.getUsername(), member.getPassword(),
					AuthorityUtils.createAuthorityList(member.getRole().toString()));
		// AuthorityUtils : 권한 문자열 간의 비교를 쉽게 할 수 있는 메서드들을 제공
		// createAuthorityList : 주어진 권한 문자열들로부터 권한 목록을 생성
		// User는 UserDetails를 구현한 클래스 (다형성)
		// 로그인성공하면 security context라고 불리는 session영역에 User객체를 저장함
		// OAuth2User : 다양한 공급자에서 제공되는 사용자 정보를 일관된 방식으로 받아오고 처리
	}
}
