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

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// memberRepo에서 사용자 정보를 검색해서
		Member member = memberRepo.findById(username)
							.orElseThrow(()->new UsernameNotFoundException("Not Found"));
		System.out.println(member); // 검색된 사용자 정보를 console에 출력해서 확인
		
		// UserDetails 타입의 객체를 생성해서 리턴
		// 여기에서 리턴된 user객체와 로그인해서 로그인 요청 정보를 비교
		return new User(member.getUsername(), member.getPassword(),
				AuthorityUtils.createAuthorityList(member.getRole().toString()));
		// User는 UserDetails를 구현한 클래스 (다형성)
		// 로그인성공하면 security context라고 불리는 session영역에 User객체를 저장함
	}
}
