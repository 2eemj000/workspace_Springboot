package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MemberInitialize implements ApplicationRunner {

	private final MemberRepository memberRepo;
	private final PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		// 같은 abcd라도 다 다르게 인코딩해줌 (암호화)
		// http://localhost:8080/h2-console 에서 DB에 입력된 값을 확인가능 (disc가 아니라 memory일때는 localhost로 들어가야함)
		memberRepo.save(Member.builder().username("member").password(encoder.encode("abcd"))
				.role(Role.ROLE_MEMBER).enabled(true).build());
		memberRepo.save(Member.builder().username("manager").password(encoder.encode("abcd"))
				.role(Role.ROLE_MANAGER).enabled(true).build());
		memberRepo.save(Member.builder().username("admin").password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN).enabled(true).build());
	}

}
