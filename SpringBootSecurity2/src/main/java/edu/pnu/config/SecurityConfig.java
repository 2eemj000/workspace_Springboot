package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 설정클래스
@EnableWebSecurity // 시큐리티 적용에 필요한 필터 객체 자동생성
public class SecurityConfig {
	
	@Bean // 시큐리티 재정의 – 암호화 빈 객체 등록
	PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	@Bean // 이 메서드가 리턴 하는 객체를 IoC 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 접근 권한 설정하기
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated() 
				// "/member/**" 경로는 인증된 사용자만 접근 가능
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") 
				// "/manager/**" 경로는 MANAGER 또는 ADMIN 역할을 가진 사용자만 접근 가능
				.requestMatchers("/admin/**").hasRole("ADMIN") 
				// "/admin/**" 경로는 ADMIN 역할을 가진 사용자만 접근 가능
				.anyRequest().permitAll()); 
				// 나머지 모든 요청은 모든 사용자에게 허용
		
		http.csrf(cf->cf.disable()); // (사이트간 요청위조 (몰라도됨..))
		
		// http.formLogin(form->{}); // SpringBoot가 제공하는 로그인화면을 쓰겠다는 설정
		http.formLogin(form->
				form.loginPage("/login") // member를 호출해서 로그인화면으로 왔을 때
					.defaultSuccessUrl("/loginSuccess",true) // 로그인 성공하면 "/loginSuccess"로 가겠다는 의미
		);
		
		// 접근 권한 없음 페이지처리
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied"));
	
		// 로그아웃처리
		http.logout(logout->logout
				.invalidateHttpSession(true) // 현재 브라우저와 연결된 세션 강제 종료
				.deleteCookies("JSESSIONID") // 세션아이디가 저장된 쿠키 삭제 (JSESSIONID는 웹 애플리케이션에서 세션을 유지하기 위해 사용되는 쿠키의 이름)
				.logoutSuccessUrl("/login")); // 로그아웃 후 이동할 url 지정해줌
	
		http.headers(hr->hr.frameOptions(fo->fo.disable()));
		
		return http.build(); // 위에 작성안하고 이것만 리턴하면 로그인화면 안뜸 (기본로그인화면 쓸지, 사용자지정으로 쓸지 설정해야함)
	}
	
//	// 테스트용 메모리 사용자 등록
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("manager")
//			.password("{noop}abcd") // {noop} : No Operation ➔ 비밀번호가 암호화되어 있지 않다는 의미
//			.roles("MANAGER");
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password("{noop}abcd")
//			.roles("ADMIN");
//	}
}
