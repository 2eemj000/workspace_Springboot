package edu.pnu.config.filter;

// 시큐리티 인증 필터

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.pnu.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// POST /login 요청이 들어오면 이 필터가 실행
@Slf4j // 자동으로 로깅 기능을 추가
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	// 인증 객체
	private final AuthenticationManager authenticationManager;
	
	// POST/login 요청이 왔을 때 인증을 시도하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
							HttpServletResponse response) throws AuthenticationException {
		// request에서 HTTP 요청의 본문에서 JSON 데이터 [username/password]를 읽어서 Member객체를 생성
		ObjectMapper mapper = new ObjectMapper();
		try {
			Member member = mapper.readValue(request.getInputStream(), Member.class);
			
			// UsernamePasswordAuthenticationToken을 생성하여 Spring Security의 AuthenticationManager를 통해 인증을 시도
			Authentication authToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());
			log.info("Attempting authentication" + member.getUsername());
			
			// 인증 진행 -> UserDetailsService의 loadUserByUsername에서 DB로부터 사용자정보를 읽어온뒤
			// 사용자 입력 정보와 비교한 뒤 자격 증명에 성공하면 Authenication객체를 만들어서 리턴
			return authenticationManager.authenticate(authToken);
		} catch (Exception e) {
			log.info(e.getMessage()); // “자격 증명에 실패하였습니다.” 로그 출력
		}
		response.setStatus(HttpStatus.UNAUTHORIZED.value()); // 자격 증명에 실패하면 응답코드 리턴
		return null;
	} 
	
	// 인증이 성공했을 때 실행되는 후처리 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, 
							FilterChain chain, Authentication authResult) throws IOException, ServletException {

		// 자격 증명이 성공하면 loadUserByUsername에서 만든 객체가 authResult에 담겨져 있음
		// authResult.getPrincipal()을 통해 인증된 사용자의 정보를 가져옴
		User user = (User)authResult.getPrincipal();
		log.info("Authentication successful"+authResult.toString());
		
		// username으로 JWT를 생성해서 Response Header - Authorization에 담아서 돌려줌
		// JWT를 생성하고 서명하여 토큰을 생성 (만료시간설정, 사용자이름 추가)
		String token = JWT.create()
						  .withExpiresAt(new Date(System.currentTimeMillis()+1000*60*1000))
						  .withClaim("username", user.getUsername())
						  .sign(Algorithm.HMAC256("edu.pnu.jwt"));
		// 생성된 JWT를 HTTP 응답의 Authorization 헤더에 추가하여 클라이언트에게 반환
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token); // Bearer로 정해져있음
		response.setStatus(HttpStatus.OK.value()); // 응답 상태 코드
}
}