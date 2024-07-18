
package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 이 클래스가 설정 클래스라고 정의 (IoC 컨테이너에 로드)
@EnableWebSecurity // 스프링 시큐리티 적용에 필요한 필터 객체들 자동 생성
public class SecurityConfig {
	
	@Bean // 시큐리티 재정의 – 암호화 빈 객체 등록
	PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
	}
	
	@Bean // 이 메서드가 리턴 하는 객체를 IoC 컨테이너에 등록하라는 지시
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  // 접근권한 설정
		http.authorizeHttpRequests(security->security
				.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll());
		
		http.csrf(cf->cf.disable());
		
		// http.formLogin(form->{}); // SpringBoot가 제공해주는 로그인 사용하겠다는 설정 (없애고 아래에 내가 만든 로그인 화면)
		
		http.formLogin(form->
					form.loginPage("/login") // Get으로 LoginController의 "/login" 호출
					.defaultSuccessUrl("/loginSuccess", true) // true는 "/member"를 호출해서 로그인 화면으로 왔을 때 로그인에 성공한 뒤 "/loginSuccess"로 이동하겠다는 의미
				); // 사용자 정의 로그인 화면
		
		http.exceptionHandling(ex->ex.accessDeniedPage("/accessDenied")); // 접근권한없음 페이지 호출
		
		http.logout(logout->logout
				.invalidateHttpSession(true) // 연결 세션 강제 종료
				.deleteCookies("JSESSIONID") // 아이디 저장 쿠키 삭제
				.logoutSuccessUrl("/login")); // 로그아웃 후 이동할 url 지정
		http.headers(hr->hr.frameOptions(fo->fo.disable())); // 호출 시 프레임만 뜨고 실행이 안되면 이 코드 추가
		
		return http.build();
	}

// 테스트용
//	@Autowired
//	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
//	auth.inMemoryAuthentication()
//		.withUser("manager")
//		.password("{noop}abcd") // {noop} : No Operation ➔ 비밀번호가 암호화되어 있지 않다는 의미
//		.roles("MANAGER");
//	auth.inMemoryAuthentication()
//		.withUser("admin")
//		.password("{noop}abcd")
//		.roles("ADMIN");
//	}
}
