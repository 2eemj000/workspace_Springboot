package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import edu.pnu.handler.OAuth2SuccessHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	// 로그인에 성공하면 임의의 사용자를 생성해서 DB에 저장하고 JWT 토큰을 만들어서 응답 헤더에 설정하는 핸들러
	private final OAuth2SuccessHandler successHandler;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		// 구글 로그인을 실행하면 DefaultOAuth2UserService가 실행됨.
		// 로그인에 성공했을 때 추가적인 작업이 필요하면 DefaultOAuth2UserService를 상속한 클래스의
		// loadUser 메소드에서 하면 됨.
		
		http.authorizeHttpRequests(authorize->authorize
				.anyRequest().permitAll())
		
			.oauth2Login(oauth2->oauth2	
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true)
				.successHandler(successHandler));
		//OAuth2 로그인이 성공하면 실행되는 successHandler, defaultSucessUrl 두 메소드가 동시에 설정되어 있으면 successHandler가 우선
		return http.build();
	
	}
}
