package edu.pnu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.pnu.domain.Member;
import edu.pnu.domain.Role;
import edu.pnu.persistance.MemberRepository;

@SpringBootTest
public class MemberInitialize { // 파일 기반
	@Autowired
	MemberRepository memRepo;
	
	PasswordEncoder encoder = new BCryptPasswordEncoder(); // 부트가 제공하는 암호화 인터페이스, 구현체
	
	@Test
	public void doWork() {
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MEMBER)
				.enabled(true).build());
		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_MANAGER)
				.enabled(true).build());
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("abcd"))
				.role(Role.ROLE_ADMIN)
				.enabled(true).build());
	}
}
